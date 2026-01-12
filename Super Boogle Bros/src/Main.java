
import client.Client;
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
        Server server = new Server(3080);
        server.start();

        // Client client = new Client("10.10.15.79", 3080);
        // client.connect();

        // client.sendObject("Hello World!");

    }


    public static void clientOnly(String args[])
    {
        Client client = new Client("10.10.15.79", 3080);
        client.connect();

        client.sendObject("Hello World!");
    }
}




