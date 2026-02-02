package client;

import actions.*;

public class UdpPacketWriter {

    public static byte[] newMovementPacket(int playerID, int dx, int dy){
        byte[] data = new byte[4];
        data[0] = (byte)UdpPacketType.MOVE.ordinal();
        data[1] = (byte)playerID;
        data[2] = (byte)(dx);
        data[3] = (byte)(dy);
        return data;
    }

    // this should actually not be a packet because clients should be sending intent (like left click)
    // the server should be making the hitbox upon receiving the packet
    // make this something like newAttackPacket and just send the player's position and character
    
}
