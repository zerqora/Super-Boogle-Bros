package client;

// holds the different types that a packet can be identified as.

// Initialized as the FIRST byte of data in a packet.
public enum UdpPacketType {
    SPAWN,
    DESPAWN,
    MOVE,
    POSITION
}
