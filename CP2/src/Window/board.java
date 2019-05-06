package Window;

import java.awt.*;                                                                           
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class board extends JPanel implements ActionListener {
    player p;                                                                                
    Image background, menuBg, block;                                                                
    Timer time;                                                                              
    private menu Menu;
    private Frame Frame;
    Random ranNum = new Random();
    final int BKMAX_X = 10000;

    public static enum STATE {MENU,GAME};

    public static STATE State = STATE.MENU;

    public board() {
        this.addMouseListener(new mouseInput());
        p = new player();                                                                    
        Menu = new menu();
        addKeyListener(new woah());                                                            
        setFocusable(true);                                                                                                                          
        ImageIcon i = new ImageIcon("mmbackground.png");                 
        menuBg = i.getImage();
        i = new ImageIcon("background.png");  
        background = i.getImage(); 
        i = new ImageIcon("images/A.png");
        block = i.getImage();
        time = new Timer(20,this);                                                           
        time.start();        
    }
    
    //this funtion will generate the tiles
    //A collision detector will be built into the player class
    public void makeTiles(){
        
    }
    
    public void actionPerformed(ActionEvent e){
        p.move();                                                                            
        repaint();                                                                           
    }
    
    public void paintComponent(Graphics g){                                                 
        if(State==STATE.GAME){
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;                                            
            g2d.drawImage(background, -p.nx, 0, null);                                   
            g2d.drawImage(background, -p.nx2, 0, null);                                 
            if(-p.nx<-575)                                                              
                p.nx=-575;                                                              
            else if(-p.nx>575)                                                         
                p.nx=575;                                                               
            if(-p.nx2<-575)                                                           
                p.nx2=-575;                                                            
            else if(-p.nx2>575)                                                        
                p.nx2=575;
            g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
        } 
        else{
            g.drawImage(menuBg, 0, 0, null);
            menu.render(g);
        }
    }
    
    

    private class woah extends KeyAdapter {                                                   
        public void keyPressed(KeyEvent e) {                                                 
            p.keyPressed(e);                                                                 
        }
          
        public void keyReleased(KeyEvent e) {                                              
            p.keyReleased(e);                                                                
        }
    }
}