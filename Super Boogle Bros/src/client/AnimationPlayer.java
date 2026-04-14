package client;

import java.awt.image.BufferedImage;
import java.util.HashMap;

// Holds the data for what image should be currently show, but does not actually draw anything. The game panel should draw it itself.
public class AnimationPlayer {

    // Name, Animation frames
    private HashMap<String, BufferedImage[]> animations;

    private BufferedImage[] currentAnimation;
    private int currentFrame;
    private BufferedImage currentImage;
    final int fps = 15;


    public int frameTimer = 0;

    public AnimationPlayer(String character) {
        animations = new HashMap<>();
        currentFrame = 0;
        currentImage = null;

        fillAnimations(character);
    }
    public void addNewAnimation(String name, BufferedImage[] animation) {
        animations.put(name, animation);
    }

    // Play the animation by name. If playing the same animation, it will reset to the first frame.
    public void playAnimation(String name) {
        currentFrame = 0;
        currentAnimation = animations.get(name);
        currentImage = currentAnimation[currentFrame];
    }

    private final void fillAnimations(String character) {
        try{
            switch (character) {
                case "WOOGLE":
                    // fill images here
                    // TODO: Make Character class so that they could have their abilities and all their animations?
            }
        }catch(Exception e){

        }
    }

    public BufferedImage getCurrentFrame() {
        return currentImage;
    }

    public void incrementTimer(){
        frameTimer++;
        if (frameTimer == fps){
            currentFrame++;
            frameTimer = 0;
        }
    }

}
