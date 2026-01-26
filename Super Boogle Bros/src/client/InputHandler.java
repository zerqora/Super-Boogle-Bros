package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// Every client has their own input handler. The input handler decides whether to send it to the UDP client or TCP client
public class InputHandler implements KeyListener {
    private int playerID;
    public InputHandler(int playerID) {
        this.playerID = playerID;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int[] desiredVelocity = new int[2];
        if (keyCode == KeyEvent.VK_A){
            desiredVelocity[0] = -1;
        }
        if (keyCode == KeyEvent.VK_D){
            desiredVelocity[0] = 1;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            desiredVelocity[1] = -1;
        }
        UdpPacketWriter.newMovementPacket(playerID, desiredVelocity[0], desiredVelocity[1]);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
