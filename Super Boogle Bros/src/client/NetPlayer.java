package client;

import actions.PlayerCharacter;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;

public class NetPlayer implements Serializable{

    private static final long serialVersionUID = 1L;
    
    public final float GRAVITY = 1.02f;
    public int id;
    public String name;

    public float posX, posY; // top left
    public float width, height;
    public float velX, velY; // server side use only

    public int health = 200;
    public PlayerCharacter character;
    public BufferedImage image;
    public Rectangle2D hitbox;
    public AnimationPlayer animationPlayer;
    public boolean isOnGround = true;

    public ArrayList<Rectangle2D.Float> hitboxes;

    

    public NetPlayer(int id, String name, ArrayList<Rectangle2D.Float> hitboxes)
    {
        this.id = id;
        this.name = name;
        posX = 0;
        posY = 0;

        width = 64f;
        height = 64f;

        hitbox = new Rectangle2D.Float(posX, posY, width, height);
        groundHitbox = hitboxes.get(0);

        this.hitboxes = hitboxes;
        //System.out.println("HITBOXES LIST " + hitboxes);
    }

    public NetPlayer()
    {
        posX = 0;
        posY = 0;

        width = 64f;
        height = 64f;
        hitbox = new Rectangle2D.Float(posX, posY, width, height);


        character = new PlayerCharacter(this);
    }

    public NetPlayer(float posX, float posY, float width, float height)
    {
        this.posX = posX;
        this.posY = posY;

        this.width = width;
        this.height = height;
    }


    //------------------------
//Angel is a bum

    public void updateMovement()
    {


        updateGravity(); // if is on ground, velY = 0, so the next function can update it still
        updateMovementVelocity();
        updateDrag();

        posY += velY;
        posX += velX;

        collidesWithGround(); // final check to see if the player hit the ground

        
        
        System.out.println("player coords : " + posX + "," + posY);
        System.out.println("ground height " + groundHitbox.getY());

        //if(posY > GROUND_HEIGHT) posY = GROUND_HEIGHT - height;

        buffer = false;

        System.out.println(velY);

        hitbox = new Rectangle2D.Float(posX, posY, width, height);


    }

    public boolean buffer = false;
    public float moveVelX;
    public float moveVelY;
    public int timer = 0;

    public final int JUMP_STRENGTH = 5;


    // takes movement from the inputs given to it by the server
    public void addMovementVelocity(float dx, float dy)
    {
        if(buffer)
        {
            return;
        }

        moveVelX += dx;

        if(isOnGround)
        {
            moveVelY += JUMP_STRENGTH * dy;
            timer = 6;
        }

        if(!isOnGround) timer--;

        if(timer <= 0 && !isOnGround && dy != 0 && doubleJumpAvailable)
        {
            System.out.println("double jump");
            velY = 0;
            gravityMult = 0;

            doubleJumpAvailable = false;
            moveVelY += 2 * JUMP_STRENGTH * dy - GRAVITY; // -gravity bc it will be added right after, gravity - gravity = 0
        }
        
        //Angel is a chud and this code gets a FUCK YOU/9


        
        buffer = true; //buffer is turned to false after the movement happens, this is to stop multiple velocities from happening before an update
    }

    //updates the movement once per frame
    public void updateMovementVelocity()
    {
        velY += moveVelY;
        velX += moveVelX;

        moveVelX = 0;
        moveVelY = 0;

        buffer = false;
    }

    


    
    //public final int GROUND_HEIGHT = 120;
    public float groundHeight = 0;
    public boolean doubleJumpAvailable = true;
    public float gravityMult = 0;

    // server use only
    public void updateGravity()
    {
        isOnGround = collidesWithGround(); //posY >= GROUND_HEIGHT;

        if(isOnGround)
        {
            velY = 0;
            doubleJumpAvailable = true;
            gravityMult = 0;
            
        }
        else
        {
            gravityMult = (gravityMult == 0) ? 1 : gravityMult; // if it's 0, make it 1, else leave it as is
            gravityMult *= GRAVITY; //makes gravityMult larger
            velY += gravityMult; // adds it to the velocity
        }
    }

    
    public final int SPEED = 2;
    
    public void updateDrag()
    {
        velX *= .8f;

        if(velX >= SPEED) velX = SPEED; 
        if(velX <= -1 * SPEED) velX = -1 * SPEED; 
    }

    public Rectangle2D groundHitbox; // just some default one

    public boolean collidesWithGround()
    {

        Point2D.Float point = new Point2D.Float(posX, posY + height);            // bottom left corner of hitbox
        Point2D.Float point2 = new Point2D.Float(posX + width, posY + height);   // bottom right corner of hitbox
        
        for(Rectangle2D groundHitboxes : hitboxes)
        {
            if(groundHitboxes.contains(point) || groundHitboxes.contains(point2)) //geeked variable names
            {
                posY =(float)(groundHitboxes.getY() - height);
                return true;
            }
        }

        return false;
    }

    public boolean collidesWithSolid(float posXInput, float posYInput)
    {
        Rectangle2D.Float point = new Rectangle2D.Float(posXInput, posYInput, width, height);

        for(Rectangle2D groundHitboxes : hitboxes)
            {
                if(groundHitboxes.intersects(point)) //geeked variable names
                {
                    isOnGround = true;
                    return true;
                }
            }

            isOnGround = false;
            return false;
    }


    public void setPosition(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;

        hitbox = new Rectangle2D.Float(posX, posY, width, height);
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
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/greatimages/TestGuy.png")));
            g2.drawImage(image, (int) posX, (int) posY, (int) width, (int) height, null);
        } 
        catch (IOException e) {}
    }

    
}

