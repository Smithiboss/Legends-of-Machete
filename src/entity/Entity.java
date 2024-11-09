package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y, speed;
    public BufferedImage down1, down2, up1, up2, right1, right2, left1, left2;
    public String direction;

    public int animationTime = 0;
    public int animationCounter = 1;

}
