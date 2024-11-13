package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 30; // default x value
        worldY = gp.tileSize * 12; // default y value
        speed = gp.playerSpeed;
        direction = "down"; // default direction

    }
    // loads every image of player from /res
    public void getPlayerImage() {

        try {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left2.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right2.png")));

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
    // update function for player
    public void update() {
        // moves the player in given direction
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            } else {
                direction = "right";
                worldX += speed;
            }
            animationTime++;
            // changes the player frame
            if (animationTime > gp.FPS/6) {
                if (animationCounter == 2) {
                    animationCounter = 1;
                }
                else {
                    animationCounter = 2;
                }
                animationTime = 0;

            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        // determines which player frame must be drawn
        switch (direction) {
            case "down":
                if (animationCounter == 1) {
                    image = down1;
                }
                else {
                    image = down2;
                }
                break;
            case "up":
                if (animationCounter == 1) {
                    image = up1;
                }
                else {
                    image = up2;
                }
                break;
            case "left":
                if (animationCounter == 1) {
                    image = left1;
                }
                else {
                    image = left2;
                }
                break;
            case "right":
                if (animationCounter == 1) {
                    image = right1;
                }
                else {
                    image = right2;
                }
                break;

        }
        // draws player in the game panel
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
