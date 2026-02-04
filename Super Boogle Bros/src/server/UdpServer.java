package server;

import client.UdpPacketType;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class UdpServer implements Runnable {

    // Every packet is sent to this socket.
    DatagramSocket socket;
    int serverPort;
    InetAddress serverAddress;
    byte[] buffer = new byte[1024];
    Server server;

    public UdpServer(Server server, InetAddress serverIP) throws SocketException {
        socket = new DatagramSocket(7777);
        this.serverPort = 7777;
        this.serverAddress = serverIP;
        this.server = server;
    }
    
    @Override
    public void run() {
        System.out.println("UDP Server listening on port " + serverPort);

        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // BLOCKS

                int length = packet.getLength();
                byte[] data = Arrays.copyOf(packet.getData(), length);

                InetAddress senderAddress = packet.getAddress();
                int senderPort = packet.getPort();

                System.out.println(
                        "Received UDP packet from " +
                                senderAddress.getHostAddress() +
                                ":" + senderPort +
                                " -> " + Arrays.toString(data)
                );

                //handlePacket(data, senderAddress, senderPort);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        new Thread(this).start();
        System.out.println("UDP Server Started");
    }
    public void handlePacket(byte[] data, InetAddress senderAddress, int senderPort) {
        int packetTypeId = data[0] & 0xFF; // important for byte conversion

        UdpPacketType type = UdpPacketType.getTypeFromId(packetTypeId);

        switch(type){
            case MOVE:
                break;
            case BASIC_ATTACK:
                break;
        }
    }
}
