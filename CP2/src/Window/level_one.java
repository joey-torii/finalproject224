/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;
import Tiles.*;
import graphics.*;
import graphics.Sprite;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFrame;
import java.lang.Object.*;

/**
 *
 * @author carlo_000
 */
public class level_one extends JFrame{
    
    public static final float GRAVITY = 0.002f;
    
    private Point pointCache = new Point();
    JFrame level_one;
    private TileMap map;
    private MapLoader mapLoader;
    private TileMapDrawer drawer;
    protected ScreenManager screen;
    
    public boolean isRunning = true;
    public int Height = 700;
    public int Width = 1300;
    
    public void run(){
 
    }
    public void update(Long elapsedTime){
        //nothing
    }
    
    public level_one() throws IOException {
        
        //start the resource manager
        mapLoader = new MapLoader(screen.getFullScreenWindow().getGraphicsConfiguration());
        
        //load up the map drawer
        drawer = new TileMapDrawer();
        drawer.setBackground(mapLoader.loadImage("background.jpg"));
        
        //load the first map
        map = mapLoader.loadMap("map1.txt");
        
    }
    //game loop to update as needed
    public void gameLoop() {
        long startTime = System.currentTimeMillis();
        long currTime = startTime;

        while (isRunning) {
            long elapsedTime =
                System.currentTimeMillis() - currTime;
            currTime += elapsedTime;

            // update
            update(elapsedTime);

            // draw the screen
            Graphics g = null;
            drawer.draw((Graphics2D) g, map, Width, Height);
            g.dispose();
        }
        
        screen.restoreScreen();
    }
    
//     public void draw(Graphics2D g) {
//        
//        drawer.draw(g, map, Width, Height);
//               
//    }
    
    
    /**
     * Gets the current map.
     */
    public TileMap getMap() {
        return map;
    }
       /**
     * Gets the tile that a Sprites collides with. Only the
     * Sprite's X or Y should be changed, not both. Returns null
     * if no collision is detected.
     */

    /**
     * Gets the tile that a Sprites collides with.Only the
 Sprite's X or Y should be changed, not both.Returns null
 if no collision is detected.
     * @param sprite
     */
    public Point getTileCollision(Sprite sprite, float newX, float newY) 
    {
        float fromX = Math.min(sprite.getX(), newX);
        float fromY = Math.min(sprite.getY(), newY);
        float toX = Math.max(sprite.getX(), newX);
        float toY = Math.max(sprite.getY(), newY);
        
        // get the tile locations
        int fromTileX = TileMapDrawer.pixelsToTiles(fromX);
        int fromTileY = TileMapDrawer.pixelsToTiles(fromY);
        int toTileX = TileMapDrawer.pixelsToTiles(
                toX + sprite.getWidth() - 1);
        int toTileY = TileMapDrawer.pixelsToTiles(
                toY + sprite.getHeight() - 1);
        
        // check each tile for a collision
        for (int x=fromTileX; x<=toTileX; x++) {
            for (int y=fromTileY; y<=toTileY; y++) {
                if (x < 0 || x >= map.getWidth() ||
                        map.getTile(x, y) != null) {
                    // collision found, return the tile
                    pointCache.setLocation(x, y);
                    return pointCache;
                }
            }
        }
        
        // no collision found
        return null;
    }
//    
// /**
//     * Updates Animation, position, and velocity of all Sprites
//     * in the current map.
//     */
//    public void update(long elapsedTime) {
//        Creature player = (Creature)map.getPlayer();
//        
//        
//        // player is dead! start map over
//        if (player.getState() == Creature.STATE_DEAD) {
//            map = mapLoader.reloadMap();
//            return;
//        }
//        
//        // get keyboard/mouse input
//        checkInput(elapsedTime);
//        
//        // update player
//        updateCreature(player, elapsedTime);
//        player.update(elapsedTime);
//        
//        // update other sprites
//        Iterator i = map.getSprites();
//        while (i.hasNext()) {
//            Sprite sprite = (Sprite)i.next();
//            if (sprite instanceof Creature) {
//                Creature creature = (Creature)sprite;
//                if (creature.getState() == Creature.STATE_DEAD) {
//                    i.remove();
//                } else {
//                    updateCreature(creature, elapsedTime);
//                }
//            }
//            // normal update
//            sprite.update(elapsedTime);
//        }
    
        private void updateCreature(Creature creature,
            long elapsedTime) {
            //apply gravity
            creature.setVelocityY(creature.getVelocityY() + GRAVITY * elapsedTime);
            
            // change x
            float dx = creature.getVelocityX();
            float oldX = creature.getX();
            float newX = oldX + dx * elapsedTime;
            Point tile =
                    getTileCollision(creature, newX, creature.getY());
            if (tile == null) {
                creature.setX(newX);
            } else {
                // line up with the tile boundary
                if (dx > 0) {
                    creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x) -
                        creature.getWidth());
                } else if (dx < 0) {
                    creature.setX(
                        TileMapDrawer.tilesToPixels(tile.x + 1));
                }
            creature.collideHorizontal();
            
            // change y
            float dy = creature.getVelocityY();
            float oldY = creature.getY();
        float newY = oldY + dy * elapsedTime;
        tile = getTileCollision(creature, creature.getX(), newY);
        if (tile == null) {
            creature.setY(newY);
        } else {
            // line up with the tile boundary
            if (dy > 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y) -
                        creature.getHeight());
            } else if (dy < 0) {
                creature.setY(
                        TileMapDrawer.tilesToPixels(tile.y + 1));
            }
            creature.collideVertical();
        }
        }
    }
//
//public static void main (String[] args) throws IOException{
//        new level_one();
//}
}
