package edu.cmu.cs.cs214.hw6.harness;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Creates a version of {@code Process} that can be restarted after it has been stopped.
 */
final class RestartableProcess extends Process {
  private final ProcessBuilder builder;
  private Process delegateProcess;
  private boolean paused = false;

  static RestartableProcess startWithBuilder(ProcessBuilder builder) throws IOException {
    return new RestartableProcess(builder);
  }

  private RestartableProcess(ProcessBuilder builder) throws IOException {
    this.builder = builder;
    delegateProcess = builder.start();
  }

  @Override
  public OutputStream getOutputStream() {
    return delegateProcess.getOutputStream();
  }

  @Override
  public InputStream getInputStream() {
    return delegateProcess.getInputStream();
  }

  @Override
  public InputStream getErrorStream() {
    return delegateProcess.getErrorStream();
  }

  @Override
  public int waitFor() throws InterruptedException {
    return delegateProcess.waitFor();
  }

  @Override
  public int exitValue() {
    return delegateProcess.exitValue();
  }

  @Override
  public void destroy() {
    // Setting pause to false prevents a destroyed process from being resumed
    paused = false;
    delegateProcess.destroy();
  }

  @Override
  public Process destroyForcibly() {
    // Setting pause to false prevents a destroyed process from being resumed
    paused = false;
    return delegateProcess.destroyForcibly();
  }

  /**
   * Allows a stopped process to be restarted. Requires that the process was previously suspended.
   * A process that was "destroyed" rather than "suspended" cannot be restarted.
   */
  void restart() {
    assert paused && !delegateProcess.isAlive();
    try {
      // Replace the old process with a new one
      delegateProcess = builder.start();
    } catch (IOException e) {
      // An IOException should never be thrown here because we are restarting a process that we
      // know has already started successfully
      System.err.println("Unexpected IOException while restarting worker process.");
      System.exit(-1);
    }
  }

  /**
   * Terminate a process with the intent that it will later be restarted. Note that this method
   * kills the underlying process because it is unable to simply suspend operation.
   */
  void stop() {
    assert !isAlive() && !paused;
    // Set the paused flag to indicate the process is allowed to be restarted
    paused = true;
    delegateProcess.destroyForcibly();
  }

  boolean isResumable() {
    return !delegateProcess.isAlive() && paused;
  }
}
