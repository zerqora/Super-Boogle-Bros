package client;

import java.nio.ByteBuffer;

public class UdpPacketWriter {

    private ByteBuffer buffer;

    public UdpPacketWriter(int size) {
        buffer = ByteBuffer.allocate(size);
    }

    public void writeByte(int b) {
        buffer.put((byte) b);
    }

    public void writeBoolean(boolean b) {
        buffer.put((byte) (b ? 1 : 0));
    }

    public byte[] toArray() {
        return buffer.array();
    }
}
