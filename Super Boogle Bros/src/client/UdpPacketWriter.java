package client;

public class UdpPacketWriter {
    public static byte[] newPlayerPacket(int playerID){
        byte[] data = new byte[2];
        data[0] = (byte) UdpPacketType.NEW_PLAYER.getId();
        data[1] = (byte) playerID;
        return data;
    }
    public static byte[] newMovementPacket(int playerID, int dx, int dy){
        byte[] data = new byte[4];
        data[0] = (byte)UdpPacketType.MOVE.getId();
        data[1] = (byte)playerID;
        data[2] = (byte)(dx);
        data[3] = (byte)(dy);
        return data;
    }

   
    public static byte[] newBasicAttackPacket(int playerID, int dx, int dy)
    {
        byte[] data = new byte[4];
        data[0] = (byte)UdpPacketType.BASIC_ATTACK.getId();
        data[1] = (byte)playerID;
        data[2] = (byte)(dx); //direction on the X, left or right
        data[3] = (byte)(dy); //direction on the Y, up or down (CAN ONLY BE EITHER X OR Y)
        return data; 
    }

    


    

    // SERVER-SENT PACKETS
    public static byte[] newPlayerPositionSnapShot(int playerID, float x, float y){
        byte[] packet = new byte[4];
        packet[0] = (byte)UdpPacketType.POSITION.getId();
        packet[1] = (byte)playerID;
        packet[2] = (byte)x;
        packet[3] = (byte)y;
        return packet;
    }

    public static byte[] newBasicAttackPacket(int playerID, int direction)
    {
        byte[] packet = new byte[4];
        packet[0] = (byte)UdpPacketType.BASIC_ATTACK.getId();
        packet[1] = (byte)playerID;
        packet[2] = (byte)direction; // this is from the moveDirections enum so it's standardized

        return packet;
    }

    






    
    // this should actually not be a packet because clients should be sending intent (like left click)
    // the server should be making the hitbox upon receiving the packet
    // make this something like newAttackPacket and just send the player's position and character
    
}
