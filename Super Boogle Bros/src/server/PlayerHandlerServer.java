package server;

import client.NetPlayer;

import java.net.InetAddress;
import java.util.HashMap;


// Handles if the player joins or leaves
public class PlayerHandlerServer {
    // ID, Player with their ID and name
    // When a player joins the server it adds them to the hash map
    public static HashMap<Integer, NetPlayer> players = new HashMap<>();
    public static HashMap<Integer, InetAddress> addresses = new HashMap<>();
    public void put(Integer id, NetPlayer netPlayer)
    {
        players.put(id, netPlayer);
    }
    public void put(InetAddress address, Integer id){
        addresses.put(id, address);
    }
    public NetPlayer[] getPlayers(){
        return players.values().toArray(new NetPlayer[0]);
    }
    public InetAddress[] getAddresses()
    {
        return addresses.values().toArray(new InetAddress[0]);
    }

    public NetPlayer get(Integer id)
    {
        return players.get(id);
    }
}
