package client;

import packets.MovementPacket;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UdpClient implements Runnable, KeyListener {
    int[] desiredVelocity = new int[2];
    public UdpClient() {

    }
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A){
            desiredVelocity[0] = -1;
        }
        if (keyCode == KeyEvent.VK_D){
            desiredVelocity[0] = 1;
        }
        if (keyCode == KeyEvent.VK_SPACE){
            desiredVelocity[1] = -1;
        }
        //sendObject(new MovementPacket(desiredVelocity));
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {

    }
}
