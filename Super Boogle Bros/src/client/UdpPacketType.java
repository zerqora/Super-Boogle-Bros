package client;

// holds the different types that a packet can be identified as.

// Initialized as the FIRST byte of data in a packet.
public enum UdpPacketType {
    SPAWN, // 0
    DESPAWN, // 1
    MOVE, // 2 and so on
    POSITION,
    HITBOX_TRIGGERED
}
