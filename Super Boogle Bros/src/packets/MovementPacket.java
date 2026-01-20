package packets;

import java.io.Serializable;

public class MovementPacket implements Serializable {
    private static final long serialVersionUID = 1L;
    int[] desiredVelocity;
    public MovementPacket(int[] desiredVelocity) {
        this.desiredVelocity = desiredVelocity;
    }
}
