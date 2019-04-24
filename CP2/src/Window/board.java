/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Window;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author carlo_000
 */
public class board extends JPanel implements ActionListener {
    player p;
    Image img;
    Timer time;
    
    public board () {
        p = new player();
        addKeyListener(new ActionListener());
        setFocusable(true);
        ImageIcon i = new ImageIcon("images/level1(2).png");
        
        
        img = i.getImage();
    }
    
    public void actionPerformed(ActionEvent e){
        p.move();
        repaint();
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(p.getImage(), p.getX(), p.getY(), null);
        g2d.drawImage(img, 0, 0, null);
    }
    
    private class ActionListener extends KeyAdapter{
        public void KeyReleased(KeyEvent e){
            p.keyReleased(e);
        }
        public void keyPressed(KeyEvent e){
            p.keyPressed(e);
        }
    }
}
