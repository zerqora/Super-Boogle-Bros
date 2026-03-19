package client;

import actions.PlayerCharacter;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;

public class NetPlayer implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public int id;
    public String name;

    public float posX, posY; // top left
    public float width, height;
    public float velX, velY; // server side use only

    public int health = 200;
    public PlayerCharacter character;

    public BufferedImage image;

    public NetPlayer(int id, String name)
    {
        this.id = id;
        this.name = name;
        posX = 0;
        posY = 0;

        width = 64f;
        height = 64f;
    }

    public NetPlayer()
    {
        posX = 0;
        posY = 0;

        width = 64f;
        height = 64f;

        character = new PlayerCharacter(this);
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

    public float[] getPosition(){
        return new float[]{(float)posX, (float)posY};
    }


    public void setImage(String image)
    {
        try
        {
            this.image = ImageIO.read(getClass().getResourceAsStream(image));
        }
        catch(IOException e)
        { 
            System.out.println("Wrong File Type");
        }
    }

    public void draw(Graphics2D g2)
    {

        try 
        {
            this.image = ImageIO.read(getClass().getResourceAsStream("/greatimages/TestGuy.png"));
            g2.drawImage(image, (int) posX, (int) posY, (int) width, (int) height, null);
        } 
        catch (IOException e) {}
    }

    
}

