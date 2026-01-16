package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import packets.AddPlayerPacket;
import packets.RemovePlayerPacket;




public class Client implements Runnable
{
    private String host;
    private int port;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private boolean running = false;

    private EventListener listener;



    // constructor
    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    // connects to the server, making a socket, and input and output streams
    // also begins a thread on the client-side
    public void connect()
    {
        try{
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            listener = new EventListener();

            // send the packet that lets the server know a player has joined
            out.writeObject(new AddPlayerPacket());

            new Thread(this).start();
        }
        catch(ConnectException e) // if no internet or server is down
        {
            System.out.println("Unable to connect to the server.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // closes the clients connection with the server
    public void close()
    {
        try {
            running = false;
            
            RemovePlayerPacket packet = new RemovePlayerPacket(); // tells the server that this client is closing by sending a RemovePlayerPacket.
            sendObject(packet);
            
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // send data to the connection

    public void sendObject(Object packet)
    {

        try{
            out.writeObject(packet);
        } 
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
    
        running = true;

        while(running)
        {
            try{
                // data coming in from the server is handled by the listener.
                Object data = in.readObject();
                listener.received(data);
            } 
            catch(ClassNotFoundException | IOException e)
            {
                e.printStackTrace();
            }
            
        }
        
        
    }
}
