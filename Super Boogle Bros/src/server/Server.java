package server;

import client.NetPlayer;
import client.UdpPacketType;
import client.UdpPacketWriter;
import java.net.InetAddress;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.net.SocketException;
import packets.AddPlayerPacket;

public class Server implements Runnable{
    

    private int port;
    private InetAddress host;

    private TcpServer tcpServer;
    private UdpServer udpServer;

    private Thread gameThread;

    private PlayerHandlerServer playerHandler;

    // Server HOLDS the data that the CLIENT DRAWS.


    public Server(int port, InetAddress host)
    {
        this.port = port;
        this.host = host;
        playerHandler = new PlayerHandlerServer();

        try
        {
            this.tcpServer = new TcpServer(this, port, host);
            this.udpServer = new UdpServer(this, host);
        
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
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        

        

        

        // send what frame of what state player is in
    }

    public void handlePacket(Byte[] data)
    {
        
        switch((int)data[0])
        {
            
            case (UdpPacketType.MOVE):
                System.err.println("hello");


            

        
        
        }
        // clients send inputs

        // decide what state to set client in based on packet
    }

    public void addPlayer(AddPlayerPacket packet)
    {
        // change this later, stores new "empty" netplayers in a hashmap
        playerHandler.put(packet.id, new NetPlayer(packet.id, packet.name));
        System.out.println("Added new player with the ID " + packet.id);
    }
    public void broadcastToAllConnections(Object packet){
        tcpServer.broadcastToAllConnections(packet);
    }

    // sends packet to all clients via UDP
    public void broadcastBytesToAllConnections(byte[] packet){}


}
