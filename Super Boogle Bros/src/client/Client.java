package client;

import javax.swing.*;
import java.net.InetAddress;

public class Client 
{

    public TcpClient tcpClient;
    public UdpClient udpClient;
    public GamePanel gamePanel;
    public InputHandler inputHandler;
    private int id;
    public NetPlayer netPlayer;
    public GameState gameState; // this is made in event listener
    

    public Client(String serverAddress)
    {
        try{
            
            tcpClient = new TcpClient(this, serverAddress, 3080); //initialize both connections to the server as well as the gamepanel
            udpClient = new UdpClient(this, InetAddress.getByName("127.0.0.1"), 7777);
            inputHandler = new InputHandler(this);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public int getId(){
        //System.out.println("Getting ID " + id);
        return id;
    }

    public void setId(int id){
        udpClient.sendPacket(UdpPacketWriter.newPlayerPacket(id));
        this.id = id;
    }

    //connect to both servers
    public void connectSockets()
    {
        tcpClient.connect();
        udpClient.start();
    }

    public void createNewPlayer()
    {
        this.netPlayer = new NetPlayer();
        
    }

    public void initializeGamePanel(GameState gameState)
    {
        gamePanel = new GamePanel(this, gameState);
        gamePanel.addNewListener(inputHandler);
    }

    public void sendObjectTcp(Object packet)
    {
        tcpClient.sendObject(packet);
    }
    
    public void sendPacketUdp(byte[] packet){
        udpClient.sendPacket(packet);
    }


    public void createWindow() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BOOOOGLE YOOOOO");

        window.add(gamePanel);

        window.pack();

        window.setVisible(true);
        window.setLocationRelativeTo(null);

        gamePanel.startGameThread();

    }

    public void initializeGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
