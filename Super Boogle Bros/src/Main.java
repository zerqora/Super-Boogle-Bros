
import client.TcpClient;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;
<<<<<<< Updated upstream

import client.UdpClient;
=======
>>>>>>> Stashed changes
import packets.NewChatPacket;
import server.TcpServer;
import server.UdpServer;


public class Main {
    public static void main(String args[]) throws UnknownHostException, SocketException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Test1");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setVisible(true);
        window.setLocationRelativeTo(null);

        gamePanel.startGameThread();


        Scanner scanner = new Scanner(System.in);

        int testCommand = scanner.nextInt();

        TcpServer tcpServer = null;
        UdpServer udpServer = null;
        if(testCommand == 1)
        {
            tcpServer = new TcpServer(3080, InetAddress.getLocalHost());
            udpServer = new UdpServer(3081, InetAddress.getLocalHost());
            tcpServer.start();
            udpServer.start();
        }


        if(testCommand == 2)
        {
            System.out.print("Enter the server address: ");
            String serverAddress = scanner.next();
            try{
                TcpClient client = new TcpClient(serverAddress, 3080);
                UdpClient udpClient = new UdpClient(InetAddress.getByName(serverAddress), 3081);
                client.connect();
                udpClient.start();
                while (true)
                {
                    String word = scanner.nextLine();
                    client.sendObject(new NewChatPacket(word));
                }
            } catch (Exception e) {
                System.out.println("Invalid server address. Failed to connect.");
            }
        }


    }
}




