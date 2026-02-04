package client;

// holds the different types that a packet can be identified as.

// Initialized as the FIRST byte of data in a packet.
public enum UdpPacketType {
    SPAWN(0),
    DESPAWN(1),
    MOVE(2),
    POSITION(3),
    BASIC_ATTACK(4),
    CHARGED_ATTACK(5),
    DASH_ATTACK(6),
    SPECIAL_ATTACK(7),
    AERIAL_ATTACK(8),
    GRAB(9),
    THROW(10),
    SHIELD(11),
    DODGE(12);

    // IDs are more reliable because they stay consistent. if the enum order changes, .ordinal() is very fragile.
    private final int id;
    UdpPacketType(int id){
        this.id = id;
    }

    // iterate through every enum and return the match with the correct id
    public static UdpPacketType getTypeFromId(int id){
        for(UdpPacketType type : UdpPacketType.values()){
            if(type.id == id){ return type;}
        }
        return null;
    }

    public int getId(){
        return id;
    }


}
