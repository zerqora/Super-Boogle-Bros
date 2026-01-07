
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
    
    private Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    public GamePanel()
    {
        this.setPreferredSize(new Dimension((int)screenWidth, (int)screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.setFocusable(true);


    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {

    }
}










/* 
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    double drawInterval = 1000000000.0 / FPS;
    double delta;
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

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta > 1)
            {
                update();
                repaint();

                delta--;
            }
        }
    }

    public void update()
    {
        player.update();
        entityManager.update();
        tileM.update();
    }

    @Override
    public  void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        try
        {
            tileM.draw(g2);
            player.draw(g2);
            entityManager.draw(g2);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        

        
        g2.dispose();


    }
}
*/