package actions;

import client.NetPlayer;
import java.awt.Rectangle;




public class Action
{

    NetPlayer player;
    String[] sprite;

    /*
        in case an action has multiple hitboxes / hurtboxes

        [frame #][hitbox #] 
    */
    Rectangle[][] hitbox; 
    Rectangle[][] hurtbox;
    
    int frame = 0;


    public Action(NetPlayer player, String[] sprite, Rectangle[][] hitbox, Rectangle[][] hurtbox)
    {
        this.player = player;
        this.sprite = sprite;
        this.hitbox = hitbox;
        this.hurtbox = hurtbox;

    }

    public void update()
    {
        frame++;
    }



}