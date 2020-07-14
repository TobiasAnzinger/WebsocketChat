package chat.client;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ClientSetup {


    String SERVER_ADDRESS;
    int SERVER_PORT;
    String name;


    public String getSERVER_ADDRESS() {
        return SERVER_ADDRESS;
    }

    public void setSERVER_ADDRESS(String SERVER_ADDRESS) {
        this.SERVER_ADDRESS = SERVER_ADDRESS;
    }

    public int getSERVER_PORT() {
        return SERVER_PORT;
    }

    public void setSERVER_PORT(int SERVER_PORT) {
        this.SERVER_PORT = SERVER_PORT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    void init() {
        enterServerAddress();
        enterUserName();
        try { TimeUnit.SECONDS.sleep(2); } catch(InterruptedException ex) {}

//        clearScreen();
    }

    public void enterUserName() {
        System.out.println("enter your name:");
        Scanner sc = new Scanner(System.in);
        setName(sc.next());
    }

    void enterServerAddress() {
        System.out.println("enter the server address with port:");
        Scanner sc = new Scanner(System.in);
        String serverAddressString;

        serverAddressString = sc.next();
        String[] parts = serverAddressString.split(":");
        setSERVER_ADDRESS(parts[0]);
        setSERVER_PORT(Integer.parseInt(parts[1]));

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
