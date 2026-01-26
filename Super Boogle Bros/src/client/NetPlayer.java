package client;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class NetPlayer {
    
    public int id;
    public String name;

    public float posX, posY;
    public float width, height;

    public NetPlayer(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public NetPlayer()
    {
        posX = 150f;
        posY = 150f;

        width = 64f;
        height = 64f;
    }

    public NetPlayer(float posX, float posY, float width, float height)
    {
        this.posX = posX;
        this.posY = posY;

        this.width = width;
        this.height = height;
    }

    public void setPosition(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;
    }


    
    public void update()
    {

    }

    public void draw(Graphics2D g2)
    {

        try 
        {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/greatimages/TestGuy.png"));
            g2.drawImage(image, (int) posX, (int) posY, (int) width, (int) height, null);
        } 
        catch (Exception e) {}
    }

    
}

