package server;

import java.net.InetAddress;

public class Endpoint {
    // holds the IP address and port of every player binded by their ID.
    private InetAddress ip;
    private int port;
    private int id;
    public Endpoint(int id, InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
        this.id = id;
    }
    public InetAddress getIp(){
        return ip;
    }
    public int getPort(){
        return port;
    }
    public int getId(){
        return id;
    }

}
