package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private int id;

    public Connection(Socket socket){
        this.socket = socket;
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

                    System.out.println(data);


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

    // Send packets
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
