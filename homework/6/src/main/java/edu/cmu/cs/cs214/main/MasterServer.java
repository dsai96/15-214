package edu.cmu.cs.cs214.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs.cs214.hw6.util.PickledClass;

/**
 * 
 * @author dsai96
 * The key connection between workers and client
 *
 */
public class MasterServer {

  public final static String SERVER_IP = "127.0.0.1";

  /**
   * 
   * @param args
   *          the first arguement is MasterServer's name, the second is its
   *          port, followed by the ports of the available worker servers
   * @throws IOException
   *           if the file cannot be found
   * @throws UnknownHostException
   *           when it can't connect to workers
   */
  public static void main(String[] args) throws UnknownHostException, IOException {
    MasterServer m = new MasterServer();
    int SOCKET_PORT = Integer.parseInt(args[1]);
    List<Integer> ports = new ArrayList<Integer>();
    for (int i = 2; i < args.length; i++) {
      ports.add(Integer.parseInt(args[i]));
    }
    // connecting to workers
    List<Socket> workerSockets = new ArrayList<Socket>();
    for (Integer port : ports) {
      Socket sock = new Socket(SERVER_IP, port);
      workerSockets.add(sock);

    }

    // connecting to client
    try (ServerSocket servsock = new ServerSocket(SOCKET_PORT)) {
      while (true) {
        Socket socket = servsock.accept(); // Get a new client connection.
        // read map/reduce
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        PickledClass mapper = (PickledClass) in.readObject();
        PickledClass reducer = (PickledClass) in.readObject();
        // send map/reduce to workers
        for (Socket sock : workerSockets) {
          ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
          // folders, intermediateFile,
          out.writeObject(mapper);
          out.writeObject(reducer);
        }

        // shuffle by sending back each of the results to masterserver

        // get back and send back to client
        try {
          servsock.close();
        } catch (IOException e) {
          System.err.println("Warning:  Got exception " + e + " while closing client connection.");
        }
      }
    } catch (IOException e) {
      System.err.println("Could not open server socket on port " + SOCKET_PORT + "." + e);
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }

  }

}
