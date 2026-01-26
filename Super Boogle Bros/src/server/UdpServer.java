package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;

public class UdpServer implements Runnable {

    // Every packet is sent to this socket.
    DatagramSocket socket;
    int serverPort;
    InetAddress serverAddress;
    byte[] buffer = new byte[1024];

    public UdpServer(int port, InetAddress serverIP) throws SocketException {
        socket = new DatagramSocket(port);
        this.serverPort = port;
        this.serverAddress = serverIP;
    }
    
    @Override
    public void run() {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        while(true){
            // is blocked until it receives a packet
            try {
                socket.receive(packet);
                System.out.println("Received packet via UDP Server: " + Arrays.toString(packet.getData()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void start() {
        new Thread(this).start();
        System.out.println("UDP Server Started");
    }
}
