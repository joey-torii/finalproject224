package Tiles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import graphics.*;
import Tiles.*;


/**
    The ResourceManager class loads and manages tile Images and
    "host" Sprites used in the game. Game Sprites are cloned from
    "host" Sprites.
*/
public class MapLoader 
{
    private ArrayList tiles;
    public int currentMap;
    private GraphicsConfiguration gc;

    // host sprites used for cloning
    private Sprite playerSprite;
    //private Sprite goalSprite;


    /**
        Creates a new ResourceManager
    */
    public MapLoader(GraphicsConfiguration gc) 
    {
        this.gc = gc;
        loadTileImages();
        loadCreatureSprites();
    }


    /**
        Gets an image from the images/ directory.
    */
    public Image loadImage(String name) 
    {
        String filename = "images/" + name;
        return new ImageIcon(filename).getImage();
    }


    public Image getMirrorImage(Image image) 
    {
        return getScaledImage(image, -1, 1);
    }


    public Image getFlippedImage(Image image) 
    {
        return getScaledImage(image, 1, -1);
    }


    private Image getScaledImage(Image image, float x, float y) 
    {

        // set up the transform to help correct gemoetric distortions similar to normalizng data
        //read about theory at https://www.mathworks.com/discovery/affine-transformation.html
        AffineTransform transform = new AffineTransform();
        transform.scale(x, y);
        transform.translate(
            (x-1) * image.getWidth(null) / 2,
            (y-1) * image.getHeight(null) / 2);

        // create a transparent (not translucent) image
        Image newImage = gc.createCompatibleImage(
            image.getWidth(null),
            image.getHeight(null),
            Transparency.BITMASK);

        // draw the transformed image
        Graphics2D g = (Graphics2D)newImage.getGraphics();
        g.drawImage(image, transform, null);
        g.dispose();

        return newImage;
    }


    public TileMap loadMap(String filename)
        throws IOException {
        ArrayList lines = new ArrayList();
        int width = 0;
        int height = 0;

        // read every line in the text file into the list
        BufferedReader reader = new BufferedReader(
            new FileReader(filename));
        while (true) {
            String line = reader.readLine();
            // no more lines to read
            if (line == null) {
                reader.close();
                break;
            }

            // add every line except for comments
            if (!line.startsWith("#")) {
                lines.add(line);
                width = Math.max(width, line.length());
            }
        }

        // parse the lines to create a TileEngine
        height = lines.size();
        TileMap newMap = new TileMap(width, height);
        for (int y=0; y<height; y++) {
            String line = (String)lines.get(y);
            for (int x=0; x<line.length(); x++) {
                char ch = line.charAt(x);

                // check if the char represents tile A, B, C etc.
                int tile = ch - 'A';
                if (tile >= 0 && tile < tiles.size()) {
                    newMap.setTile(x, y, (Image)tiles.get(tile));
                }

//eventually going to be used to create goal sprite to signal the end of th elevel                
//                else if (ch == '*') {
//                    addSprite(newMap, goalSprite, x, y);
                //}
               
            }
        }
        

        // add the player to the map
        Sprite player = (Sprite)playerSprite.clone();
        player.setX(TileMapDrawer.tilesToPixels(3));
        player.setY(lines.size());
        newMap.setPlayer(player);

        return newMap;
    }


    private void addSprite(TileMap map,
        Sprite hostSprite, int tileX, int tileY)
    {
        if (hostSprite != null) {
            // clone the sprite from the "host"
            Sprite sprite = (Sprite)hostSprite.clone();

            // center the sprite
            sprite.setX(
                TileMapDrawer.tilesToPixels(tileX) +
                (TileMapDrawer.tilesToPixels(1) -
                sprite.getWidth()) / 2);

            // bottom-justify the sprite
            sprite.setY(
                TileMapDrawer.tilesToPixels(tileY + 1) -
                sprite.getHeight());

            // add it to the map
            map.addSprite(sprite);
        }
    }


    // -----------------------------------------------------------
    // code for loading sprites and images
    // -----------------------------------------------------------


    public void loadTileImages()
    {
        // keep looking for tile A,B,C, etc. allows for easy level design
        //in a .txt document
        tiles = new ArrayList();
        char ch = 'A';
        
        while (true) 
        {
            String name = ch + ".png";
            File file = new File("images/" + name);
            if (!file.exists()) 
                break;
            
            tiles.add(loadImage(name));
            ch++;
        }
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void loadCreatureSprites() 
    {

        Image[][] images = new Image[4][];

        // load left-facing images
        images[0] = new Image[] {
            loadImage("player.PNG")          
        };

        images[1] = new Image[images[0].length];
        
        for (int i=0; i<images[0].length; i++) 
        {
            // right-facing images
            images[1][i] = getMirrorImage(images[0][i]);
     
        }

        // create creature animations
        Animation[] playerAnim = new Animation[4];

        for (int i=0; i<4; i++) 
        {
            playerAnim[i] = createPlayerAnim (images[i][0]);

        }

        // create creature sprites
        playerSprite = new Player (playerAnim[0], playerAnim[1]);
        // create "goal" sprite
        Animation anim = new Animation();
        anim.addFrame(loadImage("heart.png"), 150);
        //goalSprite = new PowerUp.Goal(anim);

    }


    private Animation createPlayerAnim(Image player)
    {
        Animation anim = new Animation();
        anim.addFrame(player, 250);
     
        return anim;
    }

}
