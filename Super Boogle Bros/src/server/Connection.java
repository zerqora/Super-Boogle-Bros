package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Server server;
    private int id;

    public Connection(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
        id = 0;
        try{
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //While the player exists
            while(socket.isConnected()){
                // Have the data and do something with it
                try{
                    // where packets come in
                    Object data = in.readObject();

                    server.handlePackets(data, this);


                }
                catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Close this socket and end the connection
    public void close() {
        try{
            in.close();
            out.close();
            socket.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    // Send packets to client
    public void sendObject(Object packet){
        try{
            out.writeObject(packet);
            // clear out the stream
            out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
