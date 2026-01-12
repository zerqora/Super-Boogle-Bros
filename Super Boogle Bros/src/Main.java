
import client.Client;
import java.util.Scanner;
import javax.swing.JFrame;
import server.Server;


public class Main {
    public static void main(String args[])
    {
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

        Server server;

        if(testCommand == 1)
        {
            server = new Server(3080);
            server.start();
        }


        if(testCommand == 2)
        {
            Client client = new Client("10.10.15.79", 3080);
            client.connect();

            while (true) 
            { 
                String word = scanner.nextLine();
                client.sendObject(word);
            }
        }

  
    }
}




