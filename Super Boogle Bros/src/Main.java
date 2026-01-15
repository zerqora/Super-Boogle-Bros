
import client.Client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JFrame;
import server.Server;


public class Main {
    public static void main(String args[]) throws UnknownHostException {
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

        Server server = null;

        if(testCommand == 1)
        {
            server = new Server(3080, InetAddress.getLocalHost());
            server.start();
        }


        if(testCommand == 2)
        {
            Client client = new Client("10.10.166.115", 3080);
            client.connect();

            while (true)
            {
                String word = scanner.nextLine();
                client.sendObject(word);
            }
        }


    }
}




