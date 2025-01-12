package utils;

import main.GamePanel;
import tile.TileManager;

public class HelpMethods {

    /**
     * Checks all 4 corners of the hitbox for possible collision
     * @return Returns true if all hitbox corners do not collide, else false
      */
    public static boolean canMove(int x, int y, int width, int height, int[][] mapData) {
        // found out you need to subtract 4 from some numbers so the collision works correctly ?????
        // checking for collision tile
        if (isCollisionTile(x, y, mapData))
            if (isCollisionTile(x + width - 4, y + height - 4, mapData))
                if (isCollisionTile(x + width - 4, y, mapData))
                    return isCollisionTile(x, y + height - 4, mapData);
        return false;
    }

    /**
     * Checks whether the tile the player is looking at is solid
     * @return Returns false if tile is solid, else true (it's inverted)
      */
    public static boolean isCollisionTile(int x, int y, int[][] mapData) {
        // calculating index
        int xIndex = x / GamePanel.tileSize;
        int yIndex = y / GamePanel.tileSize;
        // extracting number of tile
        int value = mapData[yIndex][xIndex];
        // checking for collision using the tile number
        return !TileManager.tile[value].collision;

    }
}

