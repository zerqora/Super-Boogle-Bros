package client;

import java.net.InetAddress;

public class Client 
{
    public TcpClient tcpClient;
    public UdpClient udpClient;
    public GamePanel gamePanel;
    public InputHandler inputHandler;
    private int id;
    public Client(String serverAddress, int id)
    {
        try{
            this.id = id;
            tcpClient = new TcpClient(this, serverAddress, 3080); //initialize both connections to the server as well as the gamepanel
            udpClient = new UdpClient(this, InetAddress.getByName("127.0.0.1"), 7777);
            gamePanel = new GamePanel(this);
            inputHandler = new InputHandler(this);

            gamePanel.addNewListener(inputHandler);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public int getId(){
        return id;
    }
    //connect to both servers
    public void connectSockets()
    {
        tcpClient.connect();
        udpClient.start();
    }

    public void sendObjectTcp(Object packet)
    {
        tcpClient.sendObject(packet);
    }
    public void sendPacketUdp(byte[] packet){
        udpClient.sendPacket(packet);
    }
}
