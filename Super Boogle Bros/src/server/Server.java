package server;

import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    

    private int port;
    private InetAddress host;

    private TcpServer tcpServer;
    private UdpServer udpServer;


    public Server(int port, InetAddress host)
    {
        this.port = port;
        this.host = host;

        try
        {
            this.tcpServer = new TcpServer(port, host);
            this.udpServer = new UdpServer(host);
        
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


}
