package actions;



//this is the hitbox data for one move
public class HitboxData 
{

    // [frame][hitbox] if a frame has multiple hitboxes, the length of the second array may be more than one
    // priority goes to the earlier hitboxes, so [frame][0] takes priority over [frame][1]
    
    Hitbox[][] hitboxes; 
    String moveName;
    
    public HitboxData()
    {

    }
}
