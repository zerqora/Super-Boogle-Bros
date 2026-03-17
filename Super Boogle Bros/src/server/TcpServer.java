package server;

import client.NetPlayer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import packets.AddPlayerPacket;
import packets.GameStatePacket;
import packets.NewChatPacket;
import packets.ReceiveIDPacket;

public class TcpServer implements Runnable{


    private int port; // port that the server runs on
    private boolean running = false;
    private ServerSocket serverSocket;
    private InetAddress ipAddress;
    private Server server;

    private ArrayList<Connection> connections = new ArrayList<>();

    public TcpServer(int port, InetAddress ipAddress){
        this.port = port;
        this.ipAddress = ipAddress;
        this.server = null;
        // try to make the server socket with the port provided
        try{
            serverSocket = new ServerSocket(port);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public TcpServer(Server server, int port, InetAddress ipAddress){
        this.port = port;
        this.ipAddress = ipAddress;
        this.server = server;
        // try to make the server socket with the port provided
        try{
            serverSocket = new ServerSocket(port);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
        System.out.println("Server Started on the IP address " + this.ipAddress.getHostAddress());
    }

    @Override
    public void run() {
        running = true;
        while(running){
            try{
                // accept the new socket and establish a new connection
                Socket socket = serverSocket.accept();
                iniSocket(socket);
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        // server.Server has stopped running. Shut down the server.
        shutdown();
    }

    // Initialize any new connections to the server
    private void iniSocket(Socket socket) {
        Connection connection = new Connection(socket, this );
        //System.out.println("Connection Established");
        connections.add(connection);
        new Thread(connection).start();
    }

    // Close the server
    public void shutdown() {
        running = false;

        try{
            serverSocket.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void broadcastToAllConnections(Object packet){
        for(Connection connection : connections){
            connection.sendObject(packet);
        }
    }

    // where packets come in from one connection.
    public void handlePackets(Object packet, Connection connection){
        if (packet instanceof AddPlayerPacket){
            // later, the client's id should be pulled from a file or something so they can keep their data throughout multiple playthroughs

            // create a new id for the new player
            int newId = (int) (Math.random() * 256);
            while(PlayerHandlerServer.players.containsKey(newId)){
                newId = (int) (Math.random() * 256);
            }

            //System.out.println("New ID: " + newId + " to be added to the server");
            // tell the server to add a new player
            server.addPlayer((AddPlayerPacket) packet, newId);
            connection.sendObject(new ReceiveIDPacket(newId));

            //System.out.println("Attempting to send instance of gamestate to client");

            // collect all the player ids on the server side to send to client

            
            ArrayList<Integer> ids = new ArrayList();
            for(Endpoint ep : server.endpoints)
            {
                ids.add(ep.getId());
            }

            //System.out.println(ids.size());

            connection.sendObject(new GameStatePacket(newId, ids, (HashMap<Integer, NetPlayer>) PlayerHandlerServer.players.clone())); // sends over a copy of the gamestate

            broadcastToAllConnections(packet);

        }
        if (packet instanceof NewChatPacket){
            System.out.println("Received New Chat Packet");
            broadcastToAllConnections(packet);
        }
        System.out.println(packet);
    }
}
