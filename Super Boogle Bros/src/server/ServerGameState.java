package server;

import client.NetPlayer;
import client.UdpPacketWriter;
import java.io.IOException;

public class ServerGameState {
    
    
    public Server server;
    public PlayerHandlerServer playerHandler;

    public ServerGameState(Server server)
    {
        this.server = server;
        playerHandler = this.server.playerHandler;
    }

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
    
}
