package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MessageReceiver implements Runnable {

    Socket server;


    public MessageReceiver(Socket server) {
        this.server = server;
    }

    @Override
    public void run() {

        try {
            BufferedReader receive = new BufferedReader(new InputStreamReader(server.getInputStream()));

            while (true) {
                try {
                    System.out.println(receive.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
