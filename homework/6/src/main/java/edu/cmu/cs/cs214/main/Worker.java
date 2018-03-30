package edu.cmu.cs.cs214.main;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import edu.cmu.cs.cs214.hw6.util.PickledClass;

/**
 * 
 * @author dsai96 The server doing the map and reduce functions that are
 *         recieved from the MasterServer
 *
 */
public class Worker {

  public Worker(int SOCKET_PORT, String path) {
    try (ServerSocket servsock = new ServerSocket(SOCKET_PORT)) {
      while (true) {
        Socket socket = servsock.accept(); // Get a connection from MasterServer
        // read map/reduce
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // Read PickledClasses representing the mapper and reducer functions
        Function<Object, Object> mapper = (Function<Object, Object>) readPickledClass(in).newInstance();
        BiFunction<Object, Object, Object> reducer = (BiFunction<Object, Object, Object>) readPickledClass(in)
            .newInstance();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File fout = new File(timeStamp);

        try {
          servsock.close();
        } catch (IOException e1) {
          System.err.println("Warning:  Got exception " + e1 + " while closing client connection.");
        }

      }
    } catch (IOException e) {
      System.err.println("Could not open server socket on port " + SOCKET_PORT + "." + e);
    } catch (InstantiationException e1) {
      e1.printStackTrace();
    } catch (IllegalAccessException e2) {
      e2.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Worker w = new Worker(Integer.parseInt(args[0]), args[1]);
  }

  /**
   * Given an open ObjectInputStream, this method will read in a pickled class
   * and return the Class object represented by the pickled class.
   * 
   * @param in
   *          an open ObjectInputStream for us to read a pickled class from.
   * @return Class object read in from the given ObjectInputStream
   */
  private static Class<?> readPickledClass(ObjectInputStream in) {
    /**
     * Read in the PickledClass representing the reducer function. Get the Class
     * of the reducer function from the PickledClass.
     */
    try {
      return ((PickledClass) in.readObject()).revive();
    } catch (ClassNotFoundException | IOException e) {
      System.err.println("Class name mismatch in pickled function.");
      return null;
    }
  }

}
