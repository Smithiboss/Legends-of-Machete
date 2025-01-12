package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HelpingMethods {

    public static BufferedImage[] loadSprites(BufferedImage image, int width, int height, int scale, BufferedImage[] sprites) {
        for (int i = 0; i < sprites.length; i++) {
            BufferedImage subImage = image.getSubimage(i * 16, 0, width, height);

            BufferedImage scaledImage = new BufferedImage(subImage.getWidth() * scale,
                    subImage.getHeight() * scale, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.drawImage(subImage, 0, 0, subImage.getWidth() * scale,
                    subImage.getHeight() * scale, null);
            g2d.dispose();

            sprites[i] = scaledImage;
        }
        return sprites;
    }

}
