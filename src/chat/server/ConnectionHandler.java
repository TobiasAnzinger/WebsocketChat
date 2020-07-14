package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConnectionHandler implements Runnable {

    Socket client;
    int lastMessageID;

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
        lastMessageID = 0;
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

            while (true) {
                cache = printReceivedMessage(receive);
                send.println(cache);
                send.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String printReceivedMessage(BufferedReader receive) throws IOException {
        String newMessage = receive.readLine();
        newMessage = getTime() + "  " + newMessage;
        MessageHandler.addMessage(newMessage);
        System.out.println(newMessage);
        return newMessage;
    }

    String getNewMessages() {
        ArrayList<String> allMessages = MessageHandler.messages;
        StringBuilder messages = new StringBuilder();
        if (allMessages.size() > 0) {
            for (int i = lastMessageID; i < allMessages.size(); i++) {
                messages.append(allMessages.get(i));
            }
        }
        return messages.toString();
    }
}
