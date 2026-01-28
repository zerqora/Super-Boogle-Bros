package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// Every client has their own input handler. The input handler decides whether to send it to the UDP client or TCP client
public class InputHandler implements KeyListener {
    //private int playerID;
    private final Client client;
    public InputHandler(Client client) {
        this.client = client;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_SPACE) {
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
            client.sendPacketUdp(UdpPacketWriter.newMovementPacket(10, desiredVelocity[0], desiredVelocity[1]));
            System.out.println("Attempting to send new movement packet with the desired velocity of " + desiredVelocity[0] + " and " + desiredVelocity[1]);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
