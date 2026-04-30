package server;

import client.NetPlayer;
import client.UdpPacketWriter;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

public class ServerGameState {
    
    
    public Server server;
    public PlayerHandlerServer playerHandler;
    public ArrayList<Rectangle2D.Float> hitboxes;

    public ServerGameState(Server server)
    {
        this.server = server;
        playerHandler = this.server.playerHandler;

        hitboxes = new ArrayList<>();
        hitboxes.add(new Rectangle2D.Float(0f, 720f/ 4f, 1000f / 4f, 1000f / 4f));  // divide by four because the byte buffers bring light to what truth really means. (truth of the byte buffer is the truth of the byte buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer buffer bufefer)
        
    }

    public void updatePlayers()
    {
        for(Endpoint ep : server.endpoints)
        {
            int id = ep.getId();
            NetPlayer player = playerHandler.players.get(id);

            playerHandler.players.get(id).updateMovement();

            try 
            {
                server.broadcastBytesToAllConnections(UdpPacketWriter.newPlayerPositionSnapShot(id, player.posX, player.posY));
            }
            catch(IOException e) {}
        }

    }

    /* 
    public void updateGravity()
    {
        for(Endpoint ep : server.endpoints)
        {
            int id = ep.getId();
            NetPlayer player = playerHandler.players.get(id);

            playerHandler.players.get(id).updateGravity();

            try 
            {
                server.broadcastBytesToAllConnections(UdpPacketWriter.newPlayerPositionSnapShot(id, player.posX, player.posY));
            }
            catch(IOException e) {}

            
            
        }
    }
    */
}
