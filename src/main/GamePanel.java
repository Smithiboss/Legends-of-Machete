package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    static final int originalTileSize = 16; // 16x16 pixels
    static final int scale = 3; // scale for tile scaling

    public static final int tileSize = originalTileSize * scale; // 48x48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World Settings
    public final int maxWorldCol = 64;
    public final int maxWorldRow = 23;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public final int FPS = 60; // supports up to 240 FPS (see playerSpeed)
    public int playerSpeed = 4; // calculates the speed in relation to the FPS

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public TileManager tileM = new TileManager(this);

    public GamePanel() {

        // sets the preferred size to the calculated height and width
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    // creates and starts new Thread
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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
    // updates everything on the screen
    public void update() {

        player.update();
    }
    // initializes graphics and draws everything in the screen
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
