package actions;

import java.awt.Rectangle;

public class Hitbox 
{
    
    // offsets from the origin of the player (top left corner)

    float offsetX, offsetY;
    int width, height;

    float damage;
    float knockback;
    float angle;

    Rectangle hitbox;


    public Hitbox(float offsetX, float offsetY, int width, int height, float damage, float knockback, float angle)
    {
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.width = width;
        this.height = height;

        this.hitbox = new Rectangle(1, 1); // WORK HERE MAKE THIS RIGHT I KINNNNDA FORGOT HOW

        this.damage = damage;
        this.knockback = knockback;
        this.angle = angle;
    }

    




}
