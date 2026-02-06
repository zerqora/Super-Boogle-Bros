package client;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class UdpClient implements Runnable{
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
        sendPacket(UdpPacketWriter.newPlayerPacket(client.getId()));
        System.out.println("Starting Udp Client");
        new Thread(this).start();
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
    public void receivePacket(byte[] packet) {
        int packetTypeId = packet[0] & 0xFF;
        UdpPacketType type = UdpPacketType.getTypeFromId(packetTypeId);
        System.out.println("Receiving packet from the server: " + Arrays.toString(packet));
        // server should send data back to the client so it knows what to draw

    }

    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
                socket.receive(packet);

                int length = packet.getLength();
                byte[] data = Arrays.copyOf(packet.getData(), length);

                receivePacket(data);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
