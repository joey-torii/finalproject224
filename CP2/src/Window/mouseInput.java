package Window;

/*


*/

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mouseInput implements MouseListener{
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if(x>=250 && x<=350) {                 
            if(y>=200 && y<=237) {
                board.State = board.STATE.GAME;     // when mouse is pressed, play game
            }
            if(y>=250 && y<=287) {
                System.exit(1);                     // when mouse is pressed, exit game
            }
        }
        
        if(x >= 470 && x <= 570){
            if(y >= 40 && y <= 77){                 // when mouse is pressed, change profile name
                
            }
                
        }
    }
    
    public void mouseClicked(MouseEvent e){
    
    }
    
    public void mouseReleased(MouseEvent e){
    
    }
    
    public void mouseEntered(MouseEvent e){
    
    }
    
    public void mouseExited(MouseEvent e){
    
    }
}