package server;

import client.NetPlayer;

import java.net.InetAddress;
import java.util.HashMap;


// Handles if the player joins or leaves
public class PlayerHandlerServer {
    // ID, Player with their ID and name
    // When a player joins the server it adds them to the hash map
    public static HashMap<Integer, NetPlayer> players = new HashMap<>();
    public void put(Integer id, NetPlayer player)
    {
        System.out.println("New player: " + player + " ID: " + id);
        players.put(id, player);
    }
    public NetPlayer[] getPlayers(){
        return players.values().toArray(new NetPlayer[0]);
    }
    public Integer[] getIDs(){
        return(Integer[]) players.keySet().toArray();
    }
    public NetPlayer getPlayer(Integer id)
    {
        System.out.println(players.get(id));
        return players.get(id);
    }
    public boolean playerWithIdExists(Integer id)
    {
        return players.containsKey(id);
    }

}
