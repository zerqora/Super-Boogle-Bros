

import java.io.IOexception;
import java.net.ServerSocket;
public class Server implements Runnable{

    private int port; // port that the server runs on
    private boolean running = false;
    private ServerSocket serverSocket;


    public Server(int port){
        this.port = port;

        // try to make the server socket with the port provided
        try{
            serverSocket = new ServerSocket(port);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        running = true;
        while(running){
            try{
                Socket socket = serverSocket.accept();
                iniSocket(socket)
            }
            catch(IOException e){
                e.printStackTrace();
            }

        }
        shutdown();
    }

    // Initialize any new connections to the server
    private void iniSocket(Socket socket) {
        Connection connection = new Connection(socket);
        new Thread(connection).start();
    }

    // Close the server
    public void shutdown() {
        running = false;

        try{
            serverSocket.close()
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
