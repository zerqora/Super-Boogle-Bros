package server;

public class ServerPanel implements Runnable
{
    
    private Thread serverThread;

    public ServerPanel()
    {

    }

    public void startServerThread()
    {
        serverThread = new Thread(this);
        serverThread.start();
    }

    @Override
    public void run()
    {

    }
}
