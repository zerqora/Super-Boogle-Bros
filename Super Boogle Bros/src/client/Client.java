package client;

public class Client 
{
    TcpClient tcpClient = new TcpClient(null, 0); //host port
    UdpClient udpClient = new UdpClient(null, 0); // serverIP serverPort
    GamePanel gamePanel = new GamePanel();

    public Client()
    {

    }
}
