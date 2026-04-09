package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    
    private Thread gameThread;
    private InputHandler inputHandler;
    private GameState gameState;
    Client client;
    
    public GamePanel(Client client, GameState gameState)
    {
        this.client = client;
        this.gameState = gameState;
        this.setPreferredSize(new Dimension((int)1000, (int)1000));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void addNewListener(InputHandler inputHandler){
        this.addKeyListener(inputHandler);
        this.addMouseListener(inputHandler);
        this.setFocusable(true);

        this.inputHandler = inputHandler;
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }


    int fps = 60;
    double delta;
    // how many nano seconds for one frame if it is 60 fps
    double interval = 1e9 / fps; // nanoseconds in a second / fps
    long lastTime;
    long currentTime;

    @Override
    public void run()
    {

        delta = 0;
        lastTime = System.nanoTime();

        while(gameThread != null)
        {

            currentTime = System.nanoTime();
            // nano seconds since last frame / nanoseconds for one frame = percentage of what frame ur in
            delta += (currentTime - lastTime) / interval;
            lastTime = currentTime;

            // delta 100% into frame, new frame yay
            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update()
    {
        
       // update player inputs

        // update all animations here
       
       // basic attack is left click + direction
       if(inputHandler.inputLeftClick && (inputHandler.inputX != 0 || inputHandler.inputY != 0))
        {
            if(inputHandler.inputX != 0) client.sendPacketUdp(UdpPacketWriter.newBasicAttackPacket(client.getId(), inputHandler.inputX, 0)); // send attack with dx
            else if(inputHandler.inputY != 0) client.sendPacketUdp(UdpPacketWriter.newBasicAttackPacket(client.getId(), 0, inputHandler.inputY)); // send attack with dy

            System.out.println("attempting to send basic movement");
            return;
        }
       
       // movement input   ONLY IF NO OTHER INPUT
       if(inputHandler.inputX != 0 || inputHandler.inputJump)
        {
            client.sendPacketUdp(UdpPacketWriter.newMovementPacket(client.getId(), inputHandler.inputX , (inputHandler.inputJump) ? -1 : 0)); //x,y
            System.out.println("Attempting to send new movement packet from player ID " + client.getId() + " with the desired velocity of " + inputHandler.inputX + " and " + ((inputHandler.inputJump) ? -1 : 0));
        }
       
    }

    @Override
    public void paintComponent(Graphics graphic)
    {
        super.paintComponent(graphic);
        Graphics2D graphic2D = (Graphics2D) graphic;

        if(gameState.playerMap == null)
        {
            return;
        }

        for(Integer id : gameState.playerIds)
        {
            //System.out.println("in gamepanel, id in playerIds " + id);

        
                NetPlayer np = gameState.playerMap.get(id); // get the netPlayer from the gameState
                np.draw(graphic2D);
            
            
        }

        // class.draw(graphic2D), in the class, draw g2.drawImage(image, x, y, width, height)

        graphic2D.dispose();
    }
}

