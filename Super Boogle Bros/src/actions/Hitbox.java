package actions;

import java.awt.Rectangle;

public class Hitbox 
{
    
    // offsets from the origin of the player (top left corner)

    public float offsetX, offsetY;
    public int width, height;

    public float damage;
    public float knockback;
    public float angle;

    public Rectangle.Float hitbox;


    public Hitbox(float offsetX, float offsetY, int width, int height, float damage, float knockback, float angle)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.width = width;
        this.height = height;

        this.hitbox = new Rectangle.Float(offsetX, offsetY, width, height);

        this.damage = damage;
        this.knockback = knockback;
        this.angle = angle;
        
    }

    




}
