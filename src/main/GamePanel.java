package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // Screen Settings
    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3; // scale for tile scaling

    public int tileSize = originalTileSize * scale; // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    private final int FPS = 120; // supports up to 240 FPS (see playerSpeed)
    public int playerSpeed = 240/FPS; // calculates the speed in relation to the FPS

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    public GamePanel() {

        // sets the preferred size to the calculated height and width
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

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

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
