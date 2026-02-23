package server;

import client.NetPlayer;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import packets.AddPlayerPacket;

public class Server implements Runnable{
    

    private int port;
    private InetAddress host;

    private TcpServer tcpServer;
    private UdpServer udpServer;

    private Thread gameThread;

    public PlayerHandlerServer playerHandler;
    // holds ip and port of every player
    public ArrayList<Endpoint> endpoints;
    // Server HOLDS the data that the CLIENT DRAWS.


    public Server(int port, InetAddress host)
    {
        this.port = port;
        this.host = host;
        playerHandler = new PlayerHandlerServer();
        endpoints = new ArrayList<>();
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

    public void addPlayer(AddPlayerPacket packet, int id)
    {
        // change this later, stores new "empty" netplayers in a hashmap

        playerHandler.put(id, new NetPlayer(id, packet.name));


    }
    public void addNewEndpoint(int id, InetAddress ip, int port){
        // Do not add a new endpoint if it already exists
        for (Endpoint ep : endpoints){
            if (ep.getId() == id){
                return;
            }
        }
        // create a new endpoint
        Endpoint endpoint = new Endpoint(id, ip, port);
        endpoints.add(endpoint);
        System.out.println("New Endpoint Added");
        // print it out
        for(Endpoint ep : endpoints){
            System.out.println(ep.getId() + ": " + ep.getIp() + ", " + ep.getPort());
        }
    }
    public void broadcastToAllConnections(Object packet){
        tcpServer.broadcastToAllConnections(packet);
    }

    // sends packet to all clients via UDP
    public void broadcastBytesToAllConnections(byte[] packet){
        
    }


}
