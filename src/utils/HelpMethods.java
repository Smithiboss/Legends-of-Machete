package utils;

import main.GamePanel;
import tile.TileManager;

public class HelpMethods {
    // returns true if player can move, else returns false
    public static boolean canMove(int x, int y, int width, int height, int[][] mapData) {
        // found out you need to subtract 4 from some numbers now the collision works correctly but don't fucking ask why
        if (isCollisionTile(x, y, mapData))
            if (isCollisionTile(x + width - 4, y + height - 4, mapData))
                if (isCollisionTile(x + width - 4, y, mapData))
                    return isCollisionTile(x, y + height - 4, mapData);
        return false;
    }
    // returns whether the tile the player is looking at is solid (yea its inverted so what)
    public static boolean isCollisionTile(int x, int y, int[][] mapData) {
        int xIndex = x / GamePanel.tileSize;
        int yIndex = y / GamePanel.tileSize;

        int value = mapData[(int) yIndex][(int) xIndex];

        return !TileManager.tile[value].collision;

    }
}

