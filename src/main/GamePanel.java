package main;

import entities.Player;
import tile.TileManager;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Manages game loop and drawing on the screen
 */
public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    static final int originalTileSize = 16; // 16x16 pixels
    public static final int scale = 3; // scale for tile scaling

    public static final int tileSize = originalTileSize * scale; // 48x48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World Settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 30;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public final int FPS = 60; // supports up to 240 FPS (see playerSpeed)
    public int playerSpeed = 4; // calculates the speed in relation to the FPS

    Thread gameThread;
    KeyHandler keyH = new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);
    public UI ui = new UI(this);

    // Game states
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        // sets the preferred size to the calculated height and width
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setup_game() {
        gameState = playState;
    }

    /**
     * Creates and starts new game thread
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Runs the game loop
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0/FPS; // calculates the time a single frame is shown
        long currentTime;
        long lastFrame = System.nanoTime();
        int frameCounter = 0;
        long lastCheck = System.currentTimeMillis();

        while (gameThread != null) {

            currentTime = System.nanoTime();

            // calculates whether the screen needs to be updated already
            if (currentTime - lastFrame >= timePerFrame) {
                // update all information and variables
                update();
                // repaint the screen
                repaint();

                lastFrame = currentTime;
                frameCounter += 1;
            }
            // displays the FPS every second
            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("Frames: " + frameCounter);
                frameCounter = 0;
            }
        }
    }

    /**
     * Calls update methods of game objects
     */
    public void update() {
        
        if (gameState == playState) {
            player.update();
        } else if (gameState == pauseState) {
            // nothing yet
        }
    }

    /**
     * Calls draw methods of game objects
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        // Draw tiles
        tileM.draw(g2);
        // Draw player
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }
}
