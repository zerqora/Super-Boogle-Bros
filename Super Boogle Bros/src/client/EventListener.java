package client;


import packets.AddPlayerPacket;
import packets.ReceiveIDPacket;
import packets.RemovePlayerPacket;


// TCP Orientated
// Controls all the movement, inputs, and gameplay basically, by recognizing packets coming FROM the server.
public class EventListener {
    // client pov
    private TcpClient client;
    public EventListener(TcpClient client){
        this.client = client;
    }
    public void received(Object p)
    {
        if(p instanceof AddPlayerPacket)
        {
            AddPlayerPacket packet = (AddPlayerPacket) p;


            System.out.println(packet.name + " has joined the game");
        }
        else if (p instanceof RemovePlayerPacket)
        {
            RemovePlayerPacket packet = (RemovePlayerPacket) p;

            System.out.println(packet.name + " has quit the game");
        }
        if(p instanceof ReceiveIDPacket){
            ReceiveIDPacket packet = (ReceiveIDPacket) p;
            client.setID(packet.getID());
        }
    }
}
