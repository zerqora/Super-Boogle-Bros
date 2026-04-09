
import client.Client;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;
import packets.NewChatPacket;
import server.Server;


public class Main {
    public static void main(String args[]) throws UnknownHostException {

        Scanner scanner = new Scanner(System.in);

        int testCommand = scanner.nextInt();

        
        if (testCommand == 1) {
            Server server;
            server = new Server(3080, InetAddress.getLocalHost());
            server.startServers();
        }
        if (testCommand == 2) {
            System.out.print("Enter the server address: ");
            String serverAddress = scanner.next();
            try {

                Client client = new Client(serverAddress);
                client.connectSockets();

                while (true) {
                    String word = scanner.nextLine();
                    client.sendObjectTcp(new NewChatPacket(word));
                }
            } catch (Exception e) {
                System.out.println("Invalid server address. Failed to connect.");
            }
        }

        if(testCommand == 3)
            {
                Server server;
                server = new Server(3080, InetAddress.getLocalHost());
                server.startServers();

                try {
                    Client client = new Client("10.10.166.99");
                    client.connectSockets();

                    while (true) {
                        String word = scanner.nextLine();
                        client.sendObjectTcp(new NewChatPacket(word));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid server address. Failed to connect.");
                }
            }
            if(testCommand == 4)
            {
                Server server;
                server = new Server(3080, InetAddress.getLocalHost());
                server.startServers();

                try {
                    Client client = new Client("10.10.166.99");
                    client.connectSockets();

                    System.out.println();
                    System.out.println("Client 2");

                    Client client2 = new Client("10.10.166.99");
                    client2.connectSockets();
                    while (true) {
                        String word = scanner.nextLine();

                        client.sendObjectTcp(new NewChatPacket(word));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid server address. Failed to connect.");
                }
            }
    }
}




