package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

public class ServerSocket {


    public void startServer(java.net.ServerSocket server, int serverPort) throws IOException {

        printIPAddress();
        System.out.println("Server has started on 127.0.0.1:" + serverPort + "\r\nWaiting for a connection...");


        while (true) {
            Socket client = server.accept();

            Runnable connectionHandler = new ConnectionHandler(client);
            new Thread(connectionHandler).start();


//            String clientAddress;
//            clientAddress = client.getInetAddress().getHostAddress() + client.getInetAddress().getHostName();
//            System.out.println(clientAddress);
//
//            try {
//                BufferedReader rein = new BufferedReader(new InputStreamReader(client.getInputStream()));
//
//                PrintWriter senden = new PrintWriter(client.getOutputStream());
//                /**
//                 * Diese Endlosschleife ist dazu da jede anfrage der Clients anzunemen und zurück zu senden.
//                 * Eine Empfangene Nachricht wird in den Zwischenspeicher gelegt.
//                 * Anschliessend wird dieser String mit dem Wort "Antwort versehen" und zurück gesendet.
//                 */
//                String cache;
//                while ((cache = rein.readLine()) != null) {
//                    cache = getTime() + "  " + cache;
//                    senden.println(cache);
//                    senden.flush();
//                    System.out.println(cache);
//                }
//
//            } catch (Exception e) {
//
//            }
        }

    }

    String getTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatter.format(date);

    }

    public String printIPAddress() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (n.getName().equals("en0") && i.getHostAddress().contains(".")) {
                    String ip = i.getHostAddress();
                    System.out.println("the server ip is: " + ip);

                    return ip;
                }
            }
        }
        return null;
    }

}
