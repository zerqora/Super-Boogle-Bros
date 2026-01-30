package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import packets.AddPlayerPacket;
import packets.NewChatPacket;

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
        Connection connection = new Connection(socket, this);
        System.out.println("Connection Established");
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
            System.out.println("Received Add Player Packet");
            server.addPlayer( (AddPlayerPacket) packet);
            broadcastToAllConnections(packet);
        }
        if (packet instanceof NewChatPacket){
            System.out.println("Received New Chat Packet");
            broadcastToAllConnections(packet);
        }
        System.out.println(packet);
    }
}
