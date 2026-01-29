package client;

import java.io.IOException;
import java.net.*;

public class UdpClient {
    // Notice how the UDP client doesn't hold who this belongs to. Each packet should have the player ID be sent with the byte array.
    private DatagramSocket socket;
    private InetAddress serverIP;
    private int serverPort;
    public Client client;

    public UdpClient(Client client, InetAddress serverIP, int serverPort) {
        this.client = client;
        try{
            this.serverIP = serverIP;
            this.serverPort = serverPort;
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        System.out.println("Starting Udp Client");
    }

    public void sendPacket(byte[] packet) {
        // take the byte array and turn it into an actual packet that can be read by the UDP server
        DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length, serverIP, serverPort);
        try{
            socket.send(datagramPacket);
            System.out.println("Sent packet with type " + datagramPacket + " to " + datagramPacket.getAddress().getHostName());
        }
        catch(IOException e){
            throw new RuntimeException();
        }
    }

}
