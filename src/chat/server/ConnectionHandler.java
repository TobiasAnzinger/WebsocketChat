package chat.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectionHandler implements Runnable {

    Socket client;


    public ConnectionHandler(Socket client) {
        this.client = client;
    }


    String getTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return formatter.format(date);

    }


    @Override
    public void run() {

        String clientAddress;
//        clientAddress = client.getInetAddress().getHostAddress() + client.getInetAddress().getHostName();
        clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("a new client with ip address: " + clientAddress + " connected");

        try {
            PrintWriter send = new PrintWriter(client.getOutputStream());
            BufferedReader receive = new BufferedReader(new InputStreamReader(client.getInputStream()));

            send.println("you are connected");
            send.flush();
            String cache = getNewMessages();


            while ((cache = receive.readLine()) != null) {

                if(!receive.readLine().equals("")){
                System.out.println(receive.readLine());
                }
                cache =  "\n" + getTime() + "  " + cache;
                send.println(cache);
                send.flush();
//                System.out.println(cache);
            }

        } catch (Exception e) {

        }
    }

    String getNewMessages(){
        String messages = null;

        return messages;
    }
}
