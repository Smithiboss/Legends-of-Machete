package ui;

import main.GamePanel;
import utils.HelpingMethods;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI_Heart {

    GamePanel gp;
    BufferedImage image;
    BufferedImage[] sprites = new BufferedImage[6];

    public UI_Heart(GamePanel gp) {
        this.gp = gp;

        importImage();
        HelpingMethods.loadSprites(image, 16, 16, 3, sprites);
    }

    /**
     * Import the image containing heart sprites
     */
    public void importImage() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/ui/heart_sprites.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
