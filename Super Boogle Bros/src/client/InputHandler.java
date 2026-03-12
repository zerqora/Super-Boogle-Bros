package client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
// Every client has their own input handler. The input handler decides whether to send it to the UDP client or TCP client
public class InputHandler implements KeyListener, MouseListener {
    //private int playerID;
    private final Client client;
    public InputHandler(Client client) {
        this.client = client;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    int inputX;  //input of a or d for left and right, respective
    int inputY; // same for w and s
    boolean inputJump; //space
    boolean inputLeftClick;
    boolean inputRightClick;

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
    

        if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_A) inputX = ((keyCode == KeyEvent.VK_D) ? 1 : 0) - ((keyCode == KeyEvent.VK_A) ? 1 : 0);
        if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_W) inputY = ((keyCode == KeyEvent.VK_S) ? 1 : 0) - ((keyCode == KeyEvent.VK_W) ? 1 : 0);

        if(keyCode == KeyEvent.VK_SPACE) inputJump = true;

    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_D) inputX = 0;
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_W) inputY = 0;
        
        if(keyCode == KeyEvent.VK_SPACE) inputJump = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        inputLeftClick = SwingUtilities.isLeftMouseButton(e);
        inputRightClick = SwingUtilities.isRightMouseButton(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        inputLeftClick = !SwingUtilities.isLeftMouseButton(e);
        inputRightClick = !SwingUtilities.isRightMouseButton(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
    }
}
