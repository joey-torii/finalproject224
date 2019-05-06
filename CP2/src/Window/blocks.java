/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

/**
 *
 * @author carlo_000
 */
public class blocks {
    private static final int BLOCK_SIZE = 64;
    private static final int BLOCK_BITS = 6;
    
    //converts a pixel position to a tile position
    public static int pixelsToTiles(float pixels){
        return pixelsToTiles(Math.round(pixels));
    }
    
    public static int pixelsToTiles(int pixels){
        //shifting used
        return pixels >> BLOCK_BITS;
        
        //for tiles that arent a power of two
        return (int)Math.floor((float)pixels / BLOCK_SIZE);
        
    }
}
