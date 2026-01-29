package client;


import packets.AddPlayerPacket;
import packets.RemovePlayerPacket;


// TCP Orientated
// Controls all the movement, inputs, and gameplay basically, by recognizing packets coming FROM the server.
public class EventListener {
    

    public void received(Object p)
    {
        if(p instanceof AddPlayerPacket)
        {
            AddPlayerPacket packet = (AddPlayerPacket) p;
            PlayerHandlerClient.players.put(packet.id, new NetPlayer(packet.id, packet.name));

            System.out.println(packet.name + " has joined the game");
        }
        else if (p instanceof RemovePlayerPacket)
        {
            RemovePlayerPacket packet = (RemovePlayerPacket) p;

            System.out.println(PlayerHandlerClient.players.get(packet.id).name + " has quit the game");

            PlayerHandlerClient.players.remove(packet.id);
        }
    }
}
