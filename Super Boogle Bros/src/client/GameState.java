package client;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

public class GameState {
    
    public HashMap<Integer, NetPlayer> playerMap;
    public ArrayList<Integer> playerIds;


    public GameState(){}

    public GameState(ArrayList<Integer> playerIds, HashMap<Integer, NetPlayer> playerMap)
    { 
        this.playerMap = playerMap;
        this.playerIds = playerIds;
    }

    //from udp server
    public void updatePosition(ByteBuffer buffer)
    {
        playerMap.get((int) buffer.get(1)).setPosition((int) buffer.get(2), (int) buffer.get(3));
    }

    

    // adds a player to this gamestate when a new client is added to the server after this client is
    public void addPlayer(int playerId, NetPlayer player)
    {
        playerMap.put(playerId, player);
    }

}



// server game state, when a new player joins send them a copy of the server game state