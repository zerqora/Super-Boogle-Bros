package actions;

import client.NetPlayer;
import client.UdpPacketType;
import java.util.HashMap;

public class PlayerCharacter
{
    // this will be filled with all the actions of the subclass
    public HashMap<Integer, Action> actions = new HashMap<>(); // the keys are the id from udp packet type
    public NetPlayer netPlayer;

    public PlayerCharacter(NetPlayer netPlayer)
    {
        this.netPlayer = netPlayer;
    }

    public void setAction(byte[] data)
    {
        
        int packetTypeId = data[0] & 0xFF; // important for byte conversion

        UdpPacketType type = UdpPacketType.getTypeFromId(packetTypeId);

        switch(type){
                
            case MOVE:
                break;
            case BASIC_ATTACK:

            
                break;
        }
    }

    // takes each action, which are specific to each character, and puts them into the actions hashmap
    public void setActions()
    {
        // OVERRIDE THIS IN EACH CHARACTER SUBCLASS
    }

    
    
}