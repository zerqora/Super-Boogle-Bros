package client;

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

    

    // adds a player to this gamestate when a new client is added to the server after this client is
    public void addPlayer(int playerId, NetPlayer player)
    {
        playerMap.put(playerId, player);
    }

}



// server game state, when a new player joins send them a copy of the server game state