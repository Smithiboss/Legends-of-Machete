package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // standard variables for every entity
    public int worldX, worldY, speed; // position on screen and speed
    public BufferedImage down1, down2, up1, up2, right1, right2, left1, left2; // all available frames of entity
    public String direction;
    // used for animation loop
    public int animationTime = 0; // time between a frame switch
    public int animationCounter = 1; // determines which frame is to be displayed

    protected Rectangle hitbox;

    public Entity() {
        initHitbox();
    }

    protected void drawHitbox(Graphics g2) {
        g2.setColor(Color.pink);
        g2.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    private void initHitbox() {
        hitbox = new Rectangle(0, 0, 48, 48);
    }

    public void updateHitbox(int screenX, int screenY) {
        hitbox.x = screenX;
        hitbox.y = screenY;
    }

}
