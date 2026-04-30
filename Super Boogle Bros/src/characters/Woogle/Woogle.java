package characters.Woogle;

import characters.AnimationPlayer;
import characters.PlayerCharacter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Woogle extends PlayerCharacter {
    public AnimationPlayer animation;
    public Woogle(){
        animation = new AnimationPlayer();
        animation.fillAnimations(getAnimations());
    }
    public HashMap<String, BufferedImage[]> getAnimations(){
        HashMap<String, BufferedImage[]> animations = new HashMap<>();
        try {
            BufferedImage[] idleAnimation = new BufferedImage[3];
            idleAnimation[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/Woogle/idle/frame_0_delay-0.07s.png")));
            idleAnimation[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/Woogle/idle/frame_1_delay-0.07s.png")));
            idleAnimation[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/Woogle/idle/frame_2_delay-0.07s.png")));
            animations.put("idle", idleAnimation);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return animations;
    }
    public void basicAttack(){
        animation.playAnimation("basic");
    }
    public void heavyAttack(){
        animation.playAnimation("heavy");
    }
    public void sprint(){
        animation.playAnimation("sprint");
    }
    public void jump(){
        animation.playAnimation("jump");
    }

}
