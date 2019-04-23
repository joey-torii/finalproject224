package Tiles;

import java.awt.*;
import java.util.Iterator;

import graphics.Sprite;
import Tiles.Creature;

/**
    The TileMapRenderer class draws a TileMap on the screen.
    It draws all tiles, sprites, and an optional background image
    centered around the position of the player.

    If the width of background image is smaller the width of
    the tile map, the background image will appear to move
    slowly, creating a parallax background effect.

    Also, three static methods are provided to convert pixels
    to tile positions, and vice-versa.

    This TileMapRender uses a tile size of 64.
*/
public class TileMapDrawer 
{

    private static final int TILE_SIZE = 64;
    // the size in bits of the tile
    // Math.pow(2, TILE_SIZE_BITS) == TILE_SIZE
    private static final int TILE_SIZE_BITS = 6;

    private Image background;

    /**
        Converts a pixel position to a tile position.
    */
    public static int pixelsToTiles(float pixels) {
        return pixelsToTiles(Math.round(pixels));
    }


    /**
        Converts a pixel position to a tile position.
    */
    public static int pixelsToTiles(int pixels) {
        // use shifting to get correct values for negative pixels
        return pixels >> TILE_SIZE_BITS;
    }


    /**
        Converts a tile position to a pixel position.
    */
    public static int tilesToPixels(int numTiles) {

        return numTiles << TILE_SIZE_BITS;
    }


    /**
        Sets the background to draw.
    */
    public void setBackground(Image background) {
        this.background = background;
    }


    /**
        Draws the specified TileMap.
    */
    public void draw(Graphics2D g, TileMap map,
        int Width, int Height)
    {
        Sprite player = map.getPlayer();
        int mapWidth = tilesToPixels(map.getWidth());

        // get the scrolling position of the map
        // based on player's position
        int offsetX = Width / 2 -
            Math.round(player.getX()) - TILE_SIZE;
        offsetX = Math.min(offsetX, 0);
        offsetX = Math.max(offsetX, Width - mapWidth);

        // get the y offset to draw all sprites and tiles
        int offsetY = Height -
            tilesToPixels(map.getHeight());

        // draw parallax background image
        if (background != null) {
            int x = offsetX *
                (Width - background.getWidth(null)) /
                (Width - mapWidth);
            int y = Height - background.getHeight(null);

            g.drawImage(background, x, y, null);
        
        }

        // draw the visible tiles
        int firstTileX = pixelsToTiles(-offsetX);
        int lastTileX = firstTileX +
            pixelsToTiles(Width) + 1;
        for (int y=0; y<map.getHeight(); y++) {
            for (int x=firstTileX; x <= lastTileX; x++) {
                Image image = map.getTile(x, y);
                if (image != null) {
                    g.drawImage(image,
                        tilesToPixels(x) + offsetX,
                        tilesToPixels(y) + offsetY,
                        null);
                }
            }
        }

        // draw player
        g.drawImage(player.getImage(),
            Math.round(player.getX()) + offsetX,
            Math.round(player.getY()) + offsetY,
            null);
    }

}
