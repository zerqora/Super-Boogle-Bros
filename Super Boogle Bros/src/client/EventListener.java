package client;


import packets.*;



// TCP Orientated
// Controls all the movement, inputs, and gameplay basically, by recognizing packets coming FROM the server.
public class EventListener {
    // client pov
    private TcpClient tcpClient;
    private Client client;

    public EventListener(Client client, TcpClient tcpClient){
        this.tcpClient = tcpClient;
        this.client = client;

    }

    public void received(Object p)
    {
        if(p instanceof AddPlayerPacket packet)
        {
            System.out.println(packet.name + " has joined the game");
        }
        else if (p instanceof RemovePlayerPacket packet)
        {

            System.out.println(packet.name + " has quit the game");
        }
        else if(p instanceof ReceiveIDPacket packet){
            System.out.println("Received packet RecieveIDPacket " + packet.getID());
            tcpClient.setID(packet.getID());
        }
        else if(p instanceof GameStatePacket packet)
        {
            // this is only called once and alligns the server and client sides

            GameState gameState = new GameState(packet.ids, packet.players);
            client.initializeGameState(gameState);
            client.initializeGamePanel(gameState);
            client.createWindow();
        }
    }
}
