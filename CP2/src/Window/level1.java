/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.PopupMenu;
import java.util.Random;
import javax.swing.*;

/**
 *
 * @author carlo_000
 */
public class level1 extends JFrame implements ActionListener {
    protected Image level = new ImageIcon("images/level1.png").getImage();
    protected Image player = new ImageIcon("images/player.PNG").getImage();
    
    final private int levelMin_X = 1300, levelMax_X = 13000;
    
    public int levelX = 700;
    public int levelY = 350;
    public int playerX = 650;
    public int playerY = 600;
    
    private Timer time;
    
   level1(){
        setLayout(null);
        time = new Timer(30, this);
        
        time.start();
        this.setSize(1300, 700);
        
    }
   
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        
        requestFocus();
        setFocusable(true);
        
        setBackground(g);
    }
    
    void setBackground(Graphics g){
        g.drawImage(level,750-playerX,0, null);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
