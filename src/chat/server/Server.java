package chat.server;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class Server {
    public static void main(String args[]) throws Exception {

        getIpAddress();

        int serverPort = 80;

        java.net.ServerSocket server = new java.net.ServerSocket(serverPort);

        while (true){
        Socket client = server.accept();
        Runnable connectionHandler = new ConnectionHandler(client);
        new Thread(connectionHandler).start();
        }
    }


    private static void getIpAddress() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (n.getName().equals("en0") && i.getHostAddress().contains(".")) {
                    String ip = i.getHostAddress();
                    System.out.println("the server ip is: " + ip);
                }
            }
        }
    }
}