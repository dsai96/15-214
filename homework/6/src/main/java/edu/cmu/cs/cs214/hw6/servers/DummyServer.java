package edu.cmu.cs.cs214.hw6.servers;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Placeholder to show how to user the chaos monkey build configuration.
 */
public final class DummyServer implements Runnable {
  private static final long NANO_PER_MILLI = 1000;

  private final String name;
  private final ScheduledExecutorService scheduledExecutor =
          Executors.newSingleThreadScheduledExecutor();
  private final long startTime;

  private DummyServer(String name) {
    this.name = name;
    this.startTime = System.nanoTime();
  }

  /**
   * Prints out the number of milliseconds since the server started, roughly once per second.
   */
  @Override
  public void run() {
    // The two integer arguments are the initial delay, and the period. Both
    // are 1 second.
    scheduledExecutor.scheduleAtFixedRate(() -> {
      long nanosElapsed = System.nanoTime() - startTime ;
      long msElapsed = nanosElapsed / NANO_PER_MILLI;
      System.out.println(name + " running after " + msElapsed + "ms.");
    }, 1, 1, TimeUnit.SECONDS);
  }

  public static void main(String[] args) {
    System.out.println("Spawning server with arguments: " + Arrays.toString(args));
    new DummyServer(args[0]).run();
  }
}
