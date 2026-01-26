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

    

    public static byte[] newHitboxPacket(int playerID, Hitbox hitbox)
    {
        byte[] data = new byte[9];
        data[0] = (byte) (UdpPacketType.HITBOX.ordinal());
        data[1] = (byte) (playerID);
        data[2] = (byte) (hitbox.offsetX);
        data[3] = (byte) (hitbox.offsetY);
        data[4] = (byte) (hitbox.width);
        data[5] = (byte) (hitbox.height);
        data[6] = (byte) (hitbox.damage);
        data[7] = (byte) (hitbox.knockback);
        data[8] = (byte) (hitbox.angle);
        return data;
        
    }

    
}
