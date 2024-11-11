package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tile/grass.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int x = 0;
        int y = 0;

        while (x < gp.screenWidth && y < gp.screenHeight) {
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            x += gp.tileSize;
            if (x == gp.screenWidth) {
                x = 0;
                y += gp.tileSize;
            }


        }



    }
}
