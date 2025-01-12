package utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelpingMethods {

    /**
     * Extracts all sprites of a given image, resizes them and loads them into the given array (only one line yet!!)
     *
     * @param image   Image containing sprites
     * @param width   Width of a single sprite
     * @param height  Height of a single sprite
     * @param scale   Scaling number
     * @param sprites Empty array with the length of the number of sprites
     */
    public static void loadSprites(BufferedImage image, int width, int height, int scale, BufferedImage[] sprites) {
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
    }
}
