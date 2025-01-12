package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    private List<String> pressedKeys = new ArrayList<>();

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Play state handling
        if (gp.gameState == gp.playState) {
            String key = KeyEvent.getKeyText(code);

            // Set game state to pause
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.pauseState;
                return;
            }

            // If the key is one of wasd, it's added to the arrayList
            if (key.equals("W") || key.equals("A") || key.equals("S") || key.equals("D")) {
                if (!pressedKeys.contains(key)) {
                    pressedKeys.add(0, key);
                }
            }

            resolveMovement();
        }
        // Title state handling
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2)
                    gp.ui.commandNum = 0;
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState; // Sets the game in play state
                } else if (gp.ui.commandNum == 1) {
                    // None
                } else {
                    System.exit(0); // Quits the game
                }
            }
        }
        // Pause state handling
        if (gp.gameState == gp.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        String key = KeyEvent.getKeyText(code);

        pressedKeys.remove(key);

        resolveMovement();
    }

    /**
     * Resolves the key to a direction boolean
     */
    private void resolveMovement() {
        upPressed = downPressed = leftPressed = rightPressed = false;

        if (!pressedKeys.isEmpty()) {
            String firstPressed = pressedKeys.get(0);

            switch (firstPressed) {
                case "W":
                    upPressed = true;
                    break;
                case "S":
                    downPressed = true;
                    break;
                case "A":
                    leftPressed = true;
                    break;
                case "D":
                    rightPressed = true;
                    break;
            }
        }
    }
}
