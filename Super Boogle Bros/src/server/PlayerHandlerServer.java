package server;

import client.NetPlayer;
import java.util.HashMap;


// Handles if the player joins or leaves
public class PlayerHandlerServer {
    // ID, Player with their ID and name
    // When a player joins the server it adds them to the hash map
    public static HashMap<Integer, NetPlayer> players = new HashMap<>();

    public void put(Integer id, NetPlayer netPlayer)
    {
        players.put(id, netPlayer);
    }

    public NetPlayer get(Integer id)
    {
        return players.get(id);
    }
}
