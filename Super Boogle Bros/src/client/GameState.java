package client;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    
    public HashMap<Integer, NetPlayer> playerMap;
    public ArrayList<Integer> playerIds;       


    public GameState(ArrayList<Integer> playerIds, HashMap<Integer, NetPlayer> playerMap)
    { 
        this.playerMap = playerMap;
        this.playerIds = playerIds;
    }

    //from udp server
    public void updatePosition(ByteBuffer buffer)
    {
        
        int packetPlayerId = (int) buffer.get() & 0xff;
        int posX = (int) buffer.get() & 0xff;
        int posY = (int) buffer.get() & 0xff;

        // W CODE
        int drawnPosX = posX * 4;
        int drawnPosY = posY * 4;
        //System.out.println("In client gamestate, posx and posy, x " + drawnPosX + ", y " + drawnPosY);

        try
        {
            //System.out.println("IN GAMESTATE : PLAYERPACKETID : " + packetPlayerId);
            //System.out.println(playerMap.keySet().toArray()[0]);
            //System.out.println("Packet ID: " + packetPlayerId);
            playerMap.get(packetPlayerId).setPosition(drawnPosX, drawnPosY);
            

            /* 
            if(playerMap.containsKey(packetPlayerId))
            {
                System.out.println("Contains Id!");
                
            }
            else
            {
                System.out.println("does not contain id");
            }
            */
        }
        catch(NullPointerException e)
        {
            System.out.println("ERROR ERROR ERROR ERROR");
            e.printStackTrace();
        }
    }

    

    // adds a player to this gamestate when a new client is added to the server after this client is
    public void addPlayer(ByteBuffer buffer)
    {
        try{

        
        int id = (int) buffer.get() & 0xff;
        NetPlayer player = playerMap.get(id);
        System.out.println("Current Players in the Player Map: ");
        for(NetPlayer plyr : playerMap.values()){
            System.out.println(plyr.id);
        }
        playerMap.put(id, player);
        System.out.println("NEW PLAYER ADDED TO GAMESTATE: " + id + " " + player);
        }
        catch(NullPointerException e)
        {}
    }

}




// server game state, when a new player joins send them a copy of the server game state