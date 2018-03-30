package edu.cmu.cs.cs214.hw6.harness;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.stream.Collectors.toList;

/**
 * Runs a set of servers for a MapReduce system. Using a configuration file passed in via command
 * line argument, the testing harness will start a single master server, and multiple worker
 * servers. The configuration file will allow clients to configure the port numbers used, as well as
 * the probability that individual workers will fail.
 * <h2>Configuration File</h2>
 * <p>The configuration file should be a CSV file in which the first line corresponds to the master
 * server, and any remaining lines each correspond to a single worker server. The first two values
 * on each line are used to configure the fault injection features of the harness (these are
 * specified below). The remaining values on each line, if they exist, are passed to the
 * corresponding server's {@code main} method.</p>
 * <h2>Fault Injection</h2>
 * <p>As mentioned above, the first two values in each configuration line are used to configure
 * fault injection features. There are two methods of fault injection available via the harness:
 * <em>delayed kills</em> and <em>chaos</em>.</p>
 * <h3> Delayed Kills</h3>
 * <p>A delayed kill permanently deactivates the target server after a specified number of
 * milliseconds. The first argument of each configuration line will be interpreted as a {@code long}
 * number of milliseconds until a delayed kill should occur. A value of 0 in the first position will
 * be interpreted as "never kill this server".</p>
 * <h3>Chaos</h3>
 * <p>Adding chaos to a server makes it experience temporary outages. The second argument of each
 * configuration line will be interpreted as a {@code double} failure probability. This probability
 * represents the chance that the server will be inactive for a given chaos interval. Chaos
 * intervals are short periods (3s by default but you may change this). A server's given failure
 * probability is the chance that it is inaccessible for each chaos interval (i.e. the availability
 * is determined independently at each interval).</p>
 * <p>To ensure a server is always running, set its failure probability to 0.</p>
 * <p><strong>Note:</strong> The master server should have a failure probability of 0.</p>
 * <h2>Usage</h2>
 * <p>It is expected that the harness will be used mostly for build configuration at first. To run
 * the test harness without any fault injection behaviors, set both the kill delay and failure
 * probability to 0.</p>
 * <p>Once a MapReduce implementation runs reliably without any failures, faults can be added
 * slowly. Delayed kill faults are the simplest to deal with. Adding a single delayed kill will is a
 * good way to test simple fault tolerance abilities. Adding many delayed kills to workers may is a
 * good way to test how a framework responds when it cannot complete a computation. Adding chaos to
 * servers, especially simultaneously, is a way to challenge a MapReduce framework that includes
 * sophisticated mechanisms to handle faults.</p>
 * <h3>Example Configurations</h3>
 * <p> The following example configurations illustrate how to add custom arguments to configuration
 * files, as well as how to specify fault injection parameters. Clients can customize their
 * arguments to suit the needs of their specific server programs.</p>
 * <h4>Custom Arguments</h4>
 * <p>All arguments on a single line of a configuration file beyond the second argument will be
 * passed to the corresponding server's main method. These arguments can be different for each
 * server.</p>
 * <p>The example configuration files assume that the master server's {@code main} method will
 * expect its first argument to be its name, the second to be its port, and all remaining arguments
 * to be the port numbers of available worker servers. It is assumed that each worker server's
 * {@code main} method expects the first argument to be its port number, and the second to be its
 * name.</p>
 * <p><strong>Zero Failure Configuration</strong></p>
 * <p>The following configuration file will create a master server and 3 workers. None of them will
 * ever be killed by the harness.</p>
 * <pre>
 * 0, 0, Master,   8080, 8090, 8091, 8092
 * 0, 0, Worker 0, 8090
 * 0, 0, Worker 1, 8091
 * 0, 0, Worker 2, 8092
 * </pre>
 * <p>The resulting arguments for each server would be:</p>
 * <pre>
 * Master:  [ "master", "8080", "8090", "8091", "8092"]
 * Worker0: [ "worker0", "8090" ]
 * Worker1: [ "worker1", "8091" ]
 * Worker2: [ "worker2", "8092" ]
 * </pre>
 * <p><strong>Compound Failure Configuration</strong></p>
 * <p>In the following configuration, the
 * master server, and workers 1 and 2 will not be killed by the harness, but Worker 0 will be.
 * Worker 0 is expected to be inactive for 25% of chaos intervals (expected). After ten seconds
 * (1000 milliseconds) it will be permanently killed. In other words, Worker 0 will be inactive for
 * 25% of the time between 0 and 5 seconds, and will be inactive for 100% of the time after 5
 * seconds.</p>
 * <pre>
 *     0,   0, Master,   8080, 8090, 8091, 8092
 * 10000, 0.5, Worker 0, 8090
 *     0,   0, Worker 1, 8091
 *     0,   0, Worker 2, 8092
 * </pre>
 * <p>Notice that both the first and second configurations pass the same exact information to each
 * spawned server. Fault injection configuration is independent of server arguments.</p>
 */
public final class MapReduceHarness {
  // The chaos interval determines the amount of delay between the discrete chaos events, which
  // might terminate  processes.
  private static final long CHAOS_INTERVAL = 3;
  private static final TimeUnit CHAOS_INTERVAL_UNITS = TimeUnit.SECONDS;
  private static final Random RANDOM = new Random();
  private static final ScheduledExecutorService SCHEDULED_EXECUTOR =
          newSingleThreadScheduledExecutor();

  // Used when executing other java programs as Processes
  private static final String JAVA_BIN =
          System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
  private static final String CLASSPATH_FLAG = "-cp";
  private static final String JAVA_CLASSPATH = System.getProperty("java.class.path");

  // Delimiter used to separate arguments in the configuration file
  private static final String CONFIG_DELIMITER = ",";

  // Type token to pass to toArray.
  private static final String[] EMPTY_STRING_ARRAY = new String[0];

  /**
   * Creates a new server process according to the given configuration, using the given class's main
   * method.
   *
   * @param serverClass {@code Class} object for the server to be started. Must have a psvm method.
   * @param config      Configuration file to start the server with. The format of this
   *                    configuration file is specified in this class's top level javadoc.
   * @return The {@link Process} of the newly started server.
   * @throws IOException if the creation of a child process fails.
   */
  private static Process startServerProcess(Class serverClass, ServerConfig config) throws IOException {
    // The process command will consist of the java executable, the master server class name, and
    // the user arguments. It will NOT include the failure probability.
    List<String> commandTokens = new ArrayList<>();

    // The first command token is the full path to the "java" executable.
    commandTokens.add(JAVA_BIN);
    commandTokens.add(CLASSPATH_FLAG);
    commandTokens.add(JAVA_CLASSPATH);
    commandTokens.add(serverClass.getCanonicalName());
    commandTokens.addAll(Arrays.asList(config.userArgs));

    // Create a new server process, and begin scheduling chaos events
    ProcessBuilder processBuilder =
            new ProcessBuilder(commandTokens.toArray(EMPTY_STRING_ARRAY)).inheritIO();
    RestartableProcess serverProcess = RestartableProcess.startWithBuilder(processBuilder);
    injectChaos(serverProcess, config.killDelay, config.failureProbability);

    return serverProcess;
  }

  /**
   * Creates a new MapReduce harness, using the given path for the configuration file, as well as
   * the given master and worker server classes.
   *
   * @param args Arguments should be in the following format:
   *             <pre>
   *             path/to/chaos_config.csv
   *             fully.qualified.class.name.for.MasterServer
   *             fully.qualified.class.name.for.WorkerServer
   *             </pre>
   * @throws IOException if the creation of a child process fails.
   * @throws ClassNotFoundException if either the master or worker class cannot be found.
   */
  public static void main(String[] args) throws IOException, ClassNotFoundException {
    if (args.length < 3) {
      System.out.println("Usage: java MapReduceHarness <config file> <MasterClass> <WorkerClass> [master/worker args]");
      System.exit(0);
    }

    // Copy arguments into local variables to avoid confusion
    String configFilePath = args[0];
    Class masterServerClass = Class.forName(args[1]);
    Class workerServerClass = Class.forName(args[2]);

    // Construct a list holding each workers configuration information
    List<ServerConfig> allServers = new ArrayList<>();
    try (Scanner configScanner = new Scanner(new File(configFilePath))) {
      // Create a new server configuration object
      while (configScanner.hasNextLine()) {
        allServers.add(ServerConfig.configFromString(configScanner.nextLine()));
      }
    }

    if (allServers.size() < 2) {
      System.out.println("Invalid configuration: There must be one master server and at least one worker server.");
      System.exit(0);
    }

    // By convention, the first server configuration is the master, and the rest are workers
    ServerConfig masterConfig = allServers.get(0);
    List<ServerConfig> workers = allServers.subList(1, allServers.size());

    // Create each worker server
    for (ServerConfig workerConfig : workers) {
      startServerProcess(workerServerClass, workerConfig);
    }

    // Once all workers have started, start the master server
    if (masterConfig.failureProbability != 0.0) {
      System.out.println("WARNING: Master server started with non-zero probability of failure.");
    }
    startServerProcess(masterServerClass, masterConfig);
  }

  /**
   * Schedules non-deterministic failure in the future based on the chaos interval and configurable
   * failure probability of the given process.
   *
   * @param workerProcess      Process to (possibly) kill in the future.
   * @param failureProbability Probability that the process with fail at any given interval.
   */
  private static void scheduleTemporaryFailure(RestartableProcess workerProcess,
                                               final double failureProbability) {
    SCHEDULED_EXECUTOR.schedule(() -> {
      if (!workerProcess.isAlive() && !workerProcess.isResumable()) {
        // Process was permanently killed, do not schedule more actions in the future
        return;
      }
      if (RANDOM.nextDouble() < failureProbability) {
        // Sends a kill -9 signal to the created process. This is guaranteed
        // to succeed with processes created by ProcessBuilder
        if (workerProcess.isAlive()) {
          workerProcess.stop();
        }
      } else if (!workerProcess.isAlive()) {
        assert workerProcess.isResumable();
        // Process is either currently running, or can be restarted
        workerProcess.restart();
      }
      scheduleTemporaryFailure(workerProcess, failureProbability);
    }, CHAOS_INTERVAL, CHAOS_INTERVAL_UNITS);
  }

  private static void injectChaos(RestartableProcess workerProcess, final long killDelay,
                                  final double failureProbability) {
    assert 0.0 <= failureProbability && failureProbability <= 1.0;

    // If the failure probability is 0, don't bother scheduling a runnable.
    if (failureProbability > 0.0) {
      scheduleTemporaryFailure(workerProcess, failureProbability);
    }

    if (killDelay > 0.0) {
      SCHEDULED_EXECUTOR.schedule(workerProcess::destroyForcibly, killDelay, TimeUnit.MILLISECONDS);
    }
  }

  private MapReduceHarness() {
  }

  /**
   * Represents the configuration information for a single server.
   */
  private static final class ServerConfig {
    private final long killDelay;
    private final double failureProbability;
    private final String[] userArgs;

    /**
     * Creates a configuration object with the given configuration options.
     *
     * @param killDelay          Milliseconds after startup after which the server should be
     *                           permanently killed.
     * @param failureProbability Probability that the server will fail at each failure event.
     * @param userArgs           Arguments to be passed to the server on startup.
     */
    private ServerConfig(long killDelay, double failureProbability, String[] userArgs) {
      assert failureProbability >= 0.0 && failureProbability <= 1.0;
      assert userArgs != null;
      this.killDelay = killDelay;
      this.failureProbability = failureProbability;
      this.userArgs = userArgs;
    }

    /**
     * Parses a configuration string into a {@code ServerConfig} object.
     *
     * @param configString A non-empty list of arguments, separated by {@code CONFIG_DELIMITER}. The
     *                     first argument should be parseable into a {@code double} between 0 and 1,
     *                     as it represents a failure probability.
     * @return A {@code ServerConfig} object representing the parsed values of the configuration
     * string.
     */
    private static ServerConfig configFromString(String configString) {
      // Split string on the delimiter, then trim each resulting string
      List<String> tokens =
              Arrays.asList(configString.split(CONFIG_DELIMITER))
                      .stream().map(String::trim).collect(toList());

      // The first item MUST be a kill time. 0 is interpreted to mean that the process should not be
      // killed
      long killDelay = Long.parseLong(tokens.get(0));
      if (killDelay < 0) {
        System.out.println("Invalid kill delay given:\n\t");
        System.out.println(configString);
        System.out.println("The delay must be non-negative, with 0 indicating never to fail");
        System.exit(0);
      }

      // The second item MUST be a failure rate, which is a probability between 0 and one
      double failureProbability = Double.parseDouble(tokens.get(1));
      if (failureProbability < 0.0 || failureProbability > 1.0) {
        System.out.println("Invalid failure rate given:\n\t");
        System.out.println(configString);
        System.out.println("Probability values must be between 0.0 and 1.0");
        System.exit(0);
      }

      // The arguments after the first two items are user arguments
      String[] userArgs = tokens.subList(2, tokens.size()).toArray(EMPTY_STRING_ARRAY);

      return new ServerConfig(killDelay, failureProbability, userArgs);
    }
  }
}
