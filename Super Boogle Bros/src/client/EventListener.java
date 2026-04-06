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
            client.gameState.addPlayer(packet.id, new NetPlayer(packet.id, packet.name));

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
            // ISSUE -> ids is empty when the first (or maybe all) gamestate packet is sent out.

            
            // this is only called once and alligns the server and client sides
            client.gameState = new GameState(packet.ids, packet.players);
        }
    }
}
