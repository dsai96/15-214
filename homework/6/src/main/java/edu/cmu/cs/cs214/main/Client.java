
package edu.cmu.cs.cs214.main;

import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.cmu.cs.cs214.hw6.util.PickledClass;

/**
 * This client will send instructions to the masterServer in a pickled class
 */
public class Client {
  public final static int SOCKET_PORT = 13267;
  public final static String SERVER_IP = "127.0.0.1";

 /**
  * 
  * @param mapper the instance of mapper being sent to Server in a pickled class
  * @param reducer the instance of rducers being sent to Server in a pickled class
  * @throws Exception for the instantiation error that may occur
  */
  private <K, V> void miniMapReduce(Class<MyMap> mapper, Class<MyReduce> reducer) throws Exception {
    try (Socket sock = new Socket(SERVER_IP, SOCKET_PORT)) {
      ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
      out.writeObject(new PickledClass(mapper));
      out.writeObject(new PickledClass(reducer));
      out.flush();
    }
  }

  public static void main(String[] args) throws Exception {
    Client c = new Client();
    c.miniMapReduce(MyMap.class, MyReduce.class);
  }

}
