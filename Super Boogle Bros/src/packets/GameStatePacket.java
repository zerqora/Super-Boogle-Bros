package packets;

import client.NetPlayer;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

// The server sends this to the player when they first connect to the server.
// This sends the server's current game state

public class GameStatePacket implements Serializable{
    
    private static final long serialVersionUID = 1L;
    public HashMap<Integer, NetPlayer> players;
    public ArrayList<Integer> ids;
    public ArrayList<Rectangle2D.Float> hitboxes;

    public GameStatePacket(ArrayList<Integer> ids, HashMap<Integer, NetPlayer> players, ArrayList<Rectangle2D.Float> hitboxes )
    {
        this.players = players;
        this.ids = ids;
        this.hitboxes = hitboxes;
    }

    public String toString()
    {
        return "GameStatePacket with " + ids.size() + " ids.";
    }
}