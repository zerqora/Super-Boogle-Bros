
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

            System.out.println("Type 2 if you would like to create a client for yourself. ");
            testCommand = scanner.nextInt();
        }
        if (testCommand == 2) {
            System.out.print("Enter the server address: ");
            String serverAddress = scanner.next();
            try {

                Client client = new Client(serverAddress);
                client.connectSockets();
                JFrame window = new JFrame();
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setResizable(false);
                window.setTitle("BOOOOGLE YOOOOO");

                window.add(client.gamePanel);

                window.pack();

                window.setVisible(true);
                window.setLocationRelativeTo(null);

                client.gamePanel.startGameThread();

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
                    Client client = new Client("10.10.15.79");
                    client.connectSockets();
                    JFrame window = new JFrame();
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setResizable(false);
                    window.setTitle("BOOOOGLE YOOOOO");

                    window.add(client.gamePanel);

                    window.pack();

                    window.setVisible(true);
                    window.setLocationRelativeTo(null);

                    client.gamePanel.startGameThread();


                    // System.out.println();
                    // System.out.println("SECOND CLIENT");
                    // // second client for test
                    // Client client2 = new Client("10.10.15.79");
                    // client2.connectSockets();
                    // window.add(client2.gamePanel);
                    // client2.gamePanel.startGameThread();

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




