package server;

import client.NetPlayer;
import client.UdpPacketType;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
public class UdpServer implements Runnable {

    // Every packet is sent to this socket.
    DatagramSocket socket;
    int serverPort;
    InetAddress serverAddress;
    byte[] buffer = new byte[1024];
    Server server;

    public UdpServer(Server server, InetAddress serverIP) throws SocketException 
    {
        socket = new DatagramSocket(7777);
        this.serverPort = 7777;
        this.serverAddress = serverIP;
        this.server = server;
    }
    
    @Override
    public void run() 
    {
        //System.out.println("UDP Server listening on port " + serverPort);

        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // BLOCKS

                int length = packet.getLength();
                byte[] data = Arrays.copyOf(packet.getData(), length);
                //System.out.println("ID copied: " + (int) data[1]);
                InetAddress senderAddress = packet.getAddress();
                int senderPort = packet.getPort();

                /*System.out.println(
                        "Received UDP packet from " +
                                senderAddress.getHostAddress() +
                                ":" + senderPort +
                                " -> " + Arrays.toString(data)
                );*/

                handlePacket(data, senderAddress, senderPort);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        new Thread(this).start();
        //System.out.println("UDP Server Started");
    }

    public void sendPacket(byte[] data, InetAddress destination, int port) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length, destination, port);
        socket.send(packet);
    }

    public void handlePacket(byte[] data, InetAddress senderAddress, int senderPort) throws IOException {
        //for (int i = 0; i < data.length; i++) {
            //System.out.println("Byte " + i + ": " + (data[i] & 0xFF));
        //}
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int packetTypeId = buffer.get() & 0xFF; // important for byte conversion
        int packetPlayerId = buffer.get() & 0xFF;

        System.out.println("in udpserver, packedplayerid " + packetPlayerId);

        UdpPacketType type = UdpPacketType.getTypeFromId(packetTypeId);

        switch(type){
            case NEW_PLAYER:
                //System.out.println("Putting the new address in the player handler");
                // create a new Endpoint object that holds the players' ip and port. This keeps track of how packets can be broadcasted from server to client.
                server.addNewEndpoint(packetPlayerId, senderAddress, senderPort);
                break;
                
            case MOVE:
                NetPlayer player = server.playerHandler.getPlayer(packetPlayerId);
                if(player == null){
                    System.out.println("Player with ID " + packetPlayerId + " not found");
                    break;
                }
                int dx = buffer.get(2);
                int dy = buffer.get(3);
                handleMovement(player, dx, dy);

                break;
                
            case BASIC_ATTACK:

                System.out.println("Basic Attack");
                
                break;
        }
    }

    public void handleMovement(NetPlayer player, int dx, int dy) {
        if (!colliding(player)) {
            // the client should interpolate this smoothly when drawing. the server simply holds the true value of the player's position

            if(!player.buffer)
            {
                player.addMovementVelocity(dx, dy);
            }
            
        }    
    }

    public boolean colliding(NetPlayer player) 
    {
        boolean collision = false;
        float playerX = player.posX;
        float playerY = player.posY;
        return collision;
    }

    // send a player's new updated info to every client
    public void sendNewSnapshot(NetPlayer player) {

    }
}