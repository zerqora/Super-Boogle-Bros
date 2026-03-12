package server;

import client.NetPlayer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServerGameState {
    
    public Map<Integer, NetPlayer> playerMap;
    public ArrayList<Integer> playerIds;

    public ServerGameState()
    {
        playerMap = new HashMap<>();
        playerIds = new ArrayList();
        
    }

    public void addPlayer(int playerId, NetPlayer player)
    {
        playerMap.put(playerId, player);
    }
    
}
