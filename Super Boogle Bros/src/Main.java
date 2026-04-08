
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
                        
                        /* 
                        if(word.charAt(0) == 'S')
                        {
                            
                            server.endpoints.get(Integer.parseInt(word.substring(1, word.length() - 1)));
                        }
                         */

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

                    //client 2

                    System.out.println();
                    System.out.println("Client 2");

                    Client client2 = new Client("10.10.15.79");
                    client2.connectSockets();
                    JFrame window2 = new JFrame();
                    window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window2.setResizable(false);
                    window2.setTitle("BOOOOGLE YOOOOO NUMBER 2");

                    window2.add(client2.gamePanel);

                    window2.pack();

                    window2.setVisible(true);
                    window2.setLocationRelativeTo(null);

                    client2.gamePanel.startGameThread();

                    // System.out.println();
                    // System.out.println("SECOND CLIENT");
                    // // second client for test
                    // Client client2 = new Client("10.10.15.79");
                    // client2.connectSockets();
                    // window.add(client2.gamePanel);
                    // client2.gamePanel.startGameThread();

                    while (true) {
                        String word = scanner.nextLine();
                        
                        /* 
                        if(word.charAt(0) == 'S')
                        {
                            
                            server.endpoints.get(Integer.parseInt(word.substring(1, word.length() - 1)));
                        }
                         */

                        client.sendObjectTcp(new NewChatPacket(word));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid server address. Failed to connect.");
                }
            }
    }
}




