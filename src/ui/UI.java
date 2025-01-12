package ui;

import main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {

    GamePanel gp;
    Font maruMonica; // custom pixel font
    Graphics2D g2;
    UI_Heart heart;
    public int commandNum = 0; // counter for title screen menu

    public UI(GamePanel gp) {
        this.gp = gp;

        // Load fonts
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxmarumonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Create HUD objects
        heart = new UI_Heart(gp);
    }

    // MAIN DRAW METHOD

    /**
     * Manages drawing methods dependent on game state
     * @param g2 Graphics2D
     */
    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
    }

    // DRAW METHODS

    /**
     * Draws the player life
     */
    private void drawPlayerLife() {

        gp.player.currentLife = 1;

        int x = gp.tileSize/4;
        int y = gp.tileSize/4;
        int i = 0;

        // Draw current life
        while (i < gp.player.currentLife) {
            g2.drawImage(heart.sprites[1], x, y, null);
            i++;
            if (i < gp.player.currentLife) {
                g2.drawImage(heart.sprites[0], x, y, null);
            }
            i++;
            x += gp.tileSize - gp.tileSize/4;
        }
        // Fill up the rest with blank hearts
        for (int j = 0; j < Math.floor((double) (gp.player.maxLife - i) / 2); j++) {
            g2.drawImage(heart.sprites[2], x, y, null);
            x += gp.tileSize - gp.tileSize/4;
        }
    }

    /**
     * Draws the title screen
     */
    public void drawTitleScreen() {
        // Fill background black
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Draw title
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 96));
        String text = "Legends of Machete";
        int x = centeredXForText(text);
        int y = gp.tileSize * 3;
        g2.setColor(Color.gray);
        g2.drawString(text, x + 4, y + 4); // shadow
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Draw menu
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 48));
        text = "New Game";
        x = centeredXForText(text);
        y += gp.tileSize * 5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString("#", x - gp.tileSize, y);
        }

        text = "Load Game";
        x = centeredXForText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString("#", x - gp.tileSize, y);
        }

        text = "Quit";
        x = centeredXForText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString("#", x - gp.tileSize, y);
        }
    }

    /**
     * Draws the pause screen
     */
    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
        String text = "PAUSED";
        int x = centeredXForText(text);
        int y = gp.screenHeight/2;

        g2.setColor(Color.black);
        g2.drawString(text, x + 3, y + 3); // shadow
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

    }

    /**
     * Calculates x coordinate for centered text
     * @param text The text to be drawn
     * @return x coordinate for centered text
     */
    public int centeredXForText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
