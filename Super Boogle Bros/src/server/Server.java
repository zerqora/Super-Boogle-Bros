package server;

import client.NetPlayer;
import java.net.InetAddress;
import java.net.SocketException;
import packets.AddPlayerPacket;

public class Server {
    

    private int port;
    private InetAddress host;

    private TcpServer tcpServer;
    private UdpServer udpServer;

    private PlayerHandlerServer playerHandler;


    public Server(int port, InetAddress host)
    {
        this.port = port;
        this.host = host;
        playerHandler = new PlayerHandlerServer();

        try
        {
            this.tcpServer = new TcpServer(this, port, host);
            this.udpServer = new UdpServer(host);
        
        }   
        catch (SocketException e) 
        {
            e.printStackTrace();
        }
    }

    public void startServers()
    {
        this.tcpServer.start();
        this.udpServer.start();
    }

    public void addPlayer(AddPlayerPacket packet)
    {
        // change this later, stores new "empty" netplayers in a hashmap
        playerHandler.put(packet.id, new NetPlayer(packet.id, packet.name)); 
    }
    public void broadcastToAllConnections(Object packet){
        tcpServer.broadcastToAllConnections(packet);
    }
    public void broadcastBytesToAllConnections(byte[] packet){}
}
