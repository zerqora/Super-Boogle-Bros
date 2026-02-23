package packets;

import java.io.Serializable;

public class ReceiveIDPacket implements Serializable {
    // Give the client their ID
    // in the future, their ID will let them access their saved files and information
    private int id;

    public ReceiveIDPacket(int id) {
        this.id = id;
    }
    public int getID() {
        return id;
    }
}
