package chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {

        Socket server = null;

        try {
            ClientSetup setup = new ClientSetup();
            setup.init();
            server = new Socket(setup.getSERVER_ADDRESS(), setup.getSERVER_PORT());

            Runnable messageReceiver = new MessageReceiver(server);
            new Thread(messageReceiver).start();

//            BufferedReader reader = new BufferedReader(new InputStreamReader(server.getInputStream()));
            PrintWriter out = new PrintWriter(server.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String cache;
            while (true) {
//                System.out.println(reader.readLine());
                cache = input.readLine();
                out.println(setup.getName() + ": " + cache);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
