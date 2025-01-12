package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // standard variables for every entity
    public int worldX, worldY, speed; // position on screen and speed
    public BufferedImage down1, down2, up1, up2, right1, right2, left1, left2; // all available frames of entity
    public String direction;
    public int xDrawOffset, yDrawOffset;
    public int width, height;
    // used for animation loop
    public int animationTime = 0; // time between a frame switch
    public int animationCounter = 1; // determines which frame is to be displayed

    protected Rectangle hitbox;

    // for debugging
    protected void drawHitbox(Graphics g2) {
        g2.setColor(Color.pink);
        g2.drawRect(hitbox.x + xDrawOffset, hitbox.y + yDrawOffset, hitbox.width, hitbox.height);
    }

    protected void initHitbox(int x, int y, int width, int height) {
        hitbox = new Rectangle(x, y, width, height);
    }

    public void updateHitbox(int screenX, int screenY) {
        hitbox.x = screenX;
        hitbox.y = screenY;
    }

}
