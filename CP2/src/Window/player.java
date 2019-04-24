/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author carlo_000
 */
public class player {
    int x, dx;
    int y, dy;
    int dyy;
    int nx2 = 1250;
    Image still;
    
    public player(){
        ImageIcon i = new ImageIcon("player.PNG");
        still = i.getImage();
        x = 10;
        y = 172;
    }
    
    public void move(){
        x = x+dx;
        nx2 = nx2 + dx;
    }
    
    public void jump(){
        dyy = y + 5;
        y = y+dy;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Image getImage(){
        return still;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_A)
            dx = -1;
        
        if (key == KeyEvent.VK_D)
            dx = 1;
        
        if(key == KeyEvent.VK_UP){
            while(y < dyy){
                dy = 1;
            }
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT)
            dx = 0;
        
        if (key == KeyEvent.VK_RIGHT)
            dx = 0;
        if (key == KeyEvent.VK_UP){
            while (dyy <= y-5){
                dy = -1;
            }
        }
    }
}