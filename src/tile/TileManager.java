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
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();
    }
    // loads every tile into tile array
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
    // loads the map
    public void loadMap() {

        int col;
        int row = 0;

        try {
            InputStream path = getClass().getResourceAsStream("/maps/map01.txt"); // gets map file path
            BufferedReader reader = new BufferedReader(new InputStreamReader(path)); // initialize buffered reader
            String line;
            while ((line = reader.readLine())!=null && row < gp.maxScreenRow) {
                String[] numbers = line.split(" "); // reads a line of the map
                for (col=0; col < gp.maxScreenCol; col++) {
                    mapTileNum[row][col] = Integer.parseInt(numbers[col]); // parses the numbers to the main array
                }
                row++;
            }
            reader.close();
            System.out.println(Arrays.deepToString(mapTileNum)); // for debugging
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // draws the loaded array into the game panel
    public void draw(Graphics2D g2) {

        int x = 0;
        int y = 0;
        int col = 0;
        int row = 0;


        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

            int tileNum = mapTileNum[row][col]; // always contains one number throughout iteration

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null); // draws the tile
            col++;
            x += gp.tileSize;
            // changes row
            if (x == gp.screenWidth) {
                col = 0;
                x = 0;
                y += gp.tileSize;
                row++;
            }
        }
    }
}
