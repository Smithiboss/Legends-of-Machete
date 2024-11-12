package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        int col = 0;
        int row = 0;

        try {
            InputStream path = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(path));
            String line;
            while ((line = reader.readLine())!=null && row < gp.maxScreenRow) {
                String[] numbers = line.split(" ");
                for (col=0; col < gp.maxScreenCol; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            reader.close();
            System.out.println(Arrays.deepToString(mapTileNum));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int x = 0;
        int y = 0;
        int col = 0;
        int row = 0;


        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (x == gp.screenWidth) {
                col = 0;
                x = 0;
                y += gp.tileSize;
                row++;
            }


        }



    }
}
