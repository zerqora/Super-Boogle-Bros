package server;

import client.NetPlayer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import packets.GameStatePacket;

public class Server implements Runnable{
    

    private int port;
    private InetAddress host;

    private TcpServer tcpServer;
    private UdpServer udpServer;

    private Thread gameThread;
    private ServerGameState gameState;

    public PlayerHandlerServer playerHandler;
    // holds ip and port of every player
    public ArrayList<Endpoint> endpoints;
    // Server HOLDS the data that the CLIENT DRAWS.

    public Server(int port, InetAddress host)
    {
        this.port = port;
        this.host = host;
        playerHandler = new PlayerHandlerServer(this);
        endpoints = new ArrayList<>();
        gameState = new ServerGameState(this);

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
    }

    public void sendInitialGameState()
    {
        ArrayList<Integer> ids = new ArrayList<>();
        for(Endpoint ep : endpoints)
        {
            System.out.println("IN TCPSERVER " + ep.getId());
            ids.add(ep.getId());
        }

        tcpServer.broadcastToAllConnections(new GameStatePacket(ids, (HashMap<Integer, NetPlayer>) PlayerHandlerServer.players.clone()));

        gameThread = new Thread(this);
        gameThread.start();
    }

    int fps = 60;
    double delta;
    // how many nano seconds for one frame if it is 60 fps
    double interval = 1e9 / fps; // nanoseconds in a second / fps
    long lastTime;
    long currentTime;

    @Override
    public void run()
    {
        // yooo look i copied from game state isnt that lovely?

        delta = 0;
        lastTime = System.nanoTime();

        while(gameThread != null)
        {

            currentTime = System.nanoTime();
            // nano seconds since last frame / nanoseconds for one frame = percentage of what frame ur in
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            // delta 100% into frame, new frame yay
            if(delta >= 1)
            {
                update();
                delta--;
            }
        }
        
    }

    public void update()
    {
        gameState.updateGravity();
    }

    public void addPlayer(int id, NetPlayer player)
    {
        // add the player to a hashmap of more players by their id
        playerHandler.put(id, player);

    }
    
    public void addNewEndpoint(int id, InetAddress ip, int port){
        // Do not add a new endpoint if it already exists
        for (Endpoint ep : endpoints){
            if (ep.getId() == id){
                return;
            }
        }

        // create a new endpoint that holds the player's ip and port
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
    public void broadcastBytesToAllConnections(byte[] packet) throws IOException {
        for (Endpoint ep : endpoints) {
            System.out.println("Sending UDP Packet to every endpoint : " + ep.getIp() + ", " + ep.getPort());
            udpServer.sendPacket(packet, ep.getIp(), ep.getPort());
        }
    }

}
