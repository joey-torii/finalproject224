package Window;

/*


*/

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class player {
    int x, dx, y, nx, nx2, distanceTraveled;  
    protected int Width, Height;
    Image player;                                                                              
    ImageIcon playerFacingLeft = new     ImageIcon("character2.png");  
    ImageIcon playerFacingRight = new     ImageIcon("character2.png");

    public player() {
        player = playerFacingRight.getImage();                                               
        x = 25;                                                                              
        y = 280;                                                                            
        nx = -0;                                                                           
        nx2 = -575;                                                                          
        distanceTraveled = 24;
    }

    public void move() {                // gets the coordinates of the player and resets them accordingly to how the player moves
        if(x>0 && x<300) {                                                                   
            x = x+dx;                                                                        
            nx = nx+dx;                                                                      
            nx2 = nx2+dx;                                                                    
        }
        if(x<=0) {                                                                           
            x=1;                                                                             
            nx = nx+(dx*(int)0.5);                                                           
            nx2 = nx2+(dx*(int)0.5);                                                         
        }
        if(x>=300) {                                                                         
            x=299;                                                                           
            nx = nx+(dx*(int)0.5);                                                           
            nx2 = nx2+(dx*(int)0.5);                                                         
        }
        if(dx>0){
            distanceTraveled++;
        }
        else if(dx<0){
            distanceTraveled--;
        }
           
        if(distanceTraveled>104){
            x=299;
        }
            
        if(x==1 && dx<0){
             distanceTraveled++;
        }
            
        if(distanceTraveled<104){
            nx=0;
            nx2=-575;
        }
    }

    public int getX(){                  // gets X coordinate 
        return x;      
    }
        
    public int getY(){                  // gets Y coordinate
        return y; 
    }
        
    public Image getImage(){            // gets image of player at a certain point
        return player; 
    }
    
    public void getImageDimensions(){
        Width = player.getWidth(null);
        Height = player.getHeight(null);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,Width,Height);
    }

    public void keyPressed(KeyEvent e) {       // if A is pressed, move the player left                                              
        int key = e.getKeyCode();                                                            
        if(key == KeyEvent.VK_A) {                                                       
            player = playerFacingLeft.getImage();                                            
            if(distanceTraveled<104)dx=-3;else dx=-2;
        }

        if(key == KeyEvent.VK_D) {             // if D is pressed, move the player right                               
            player = playerFacingRight.getImage();                                           
            if(distanceTraveled<104)dx=3;else dx=2;
        }
        if(key == KeyEvent.VK_A){
            
        }
    }

    public void keyReleased(KeyEvent e) {                                                    
        int key = e.getKeyCode();                                                          
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_D){  // if A or Dd is released, the player stops moving
            dx = 0;
        }                                                                     
    }
}