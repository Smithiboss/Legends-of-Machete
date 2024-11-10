package entity;

import java.awt.image.BufferedImage;

public class Entity {
    // standard variables for every entity
    public int x, y, speed; // position on screen and speed
    public BufferedImage down1, down2, up1, up2, right1, right2, left1, left2; // all available frames of entity
    public String direction;
    // used for animation loop
    public int animationTime = 0; // time between a frame switch
    public int animationCounter = 1; // determines which frame is to be displayed

}
