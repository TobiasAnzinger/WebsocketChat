package chat.server;

import java.util.ArrayList;

public class MessageHandler {

    public MessageHandler() {
    }

    static ArrayList<String> messages = new ArrayList<>();

    public static synchronized void addMessage(String newMessage){
        messages.add(newMessage);
    }

}
