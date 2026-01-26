package client;

public class UdpPacketWriter {

    public static byte[] newMovementPacket(int playerID, int dx, int dy){
        byte[] data = new byte[4];
        data[0] = (byte)UdpPacketType.MOVE.ordinal();
        data[1] = (byte)playerID;
        data[2] = (byte)(dx);
        data[3] = (byte)(dy);
        return data;
    }

    
}
