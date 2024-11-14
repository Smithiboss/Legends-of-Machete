package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    public static Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap();
    }
    // loads every tile into tile array
    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png"))), false);
            tile[1] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png"))), true);
            tile[2] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water01.png"))), true);
            tile[3] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water02.png"))), true);
            tile[4] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water03.png"))), true);
            tile[5] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water04.png"))), true);
            tile[6] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water05.png"))), true);
            tile[7] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water06.png"))), true);
            tile[8] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water07.png"))), true);
            tile[9] = new Tile(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water08.png"))), true);
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
            while ((line = reader.readLine())!=null && row < gp.maxWorldRow) {
                String[] numbers = line.split(" "); // reads a line of the map
                for (col=0; col < gp.maxWorldCol; col++) {
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


        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldRow][worldCol]; // always contains one number throughout iteration

            int x = worldCol * gp.tileSize; // calculates the x coordinate of a tile on the world map
            int y = worldRow * gp.tileSize; // calculates the y coordinate of a tile on the world map
            // calculates where to draw the tile on the screen
            int screenX = x - gp.player.worldX + gp.player.screenX;
            int screenY = y - gp.player.worldY + gp.player.screenY;
            // if the position of the tile is in the specified range of the player it draws the tile on the screen
            if (x + gp.tileSize > gp.player.worldX - gp.player.screenX && x - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                   y + gp.tileSize > gp.player.worldY - gp.player.screenY && y - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }  // draws the tile
            worldCol++;
            // changes row
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
