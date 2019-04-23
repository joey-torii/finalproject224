package Window;

//import Textures.Level1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import cp2.GameEngine;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author jetor
 */
public class menu extends GameEngine{
   //GameEngine level1 = new GameEngine().run();
    public static void main (String [] ags){
        final JFrame menu = new JFrame("menu");
        JLabel title = new JLabel(new ImageIcon("images/portal.PNG"));
        JButton Start = new JButton(new ImageIcon("images/start.PNG"));
        JButton Quit = new JButton(new ImageIcon("images/quit.PNG"));
        
        Start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                final JFrame levels = new JFrame("images/Levels");
                JLabel level = new JLabel(new ImageIcon("images/levels.PNG"));
                JButton lv1 = new JButton(new ImageIcon("images/lv1.PNG"));
                lv1.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        GameEngine level1;
                        level1 = new GameEngine();
                        try {
                            level1.run();
                        } catch (InvocationTargetException ex) {
                            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                levels.setSize(1300, 700);
                level.setBounds(50, 0, 300, 200);
                lv1.setBounds(50, 200, 100, 100);
                
                levels.add(level);
                levels.add(lv1);
                levels.getContentPane().setBackground(Color.white);
                levels.setLayout(null);
                levels.setVisible(true);
                menu.setVisible(false); 
            }
        });
        
        Quit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JFrame options = new JFrame("images/");
                JLabel question =  new JLabel("Are you ugly?");
                JButton yes = new JButton("images/sad_yes.png");
                JButton no = new JButton("images/no.png");
                no.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                
                });
                
            }
        });
        
        menu.setSize(1300, 700);
        Start.setBounds(560, 400, 150, 50);
        Quit.setBounds(560, 500, 150, 50);
        title.setBounds(280, 100, 700, 200);
        
        menu.add(title);
        menu.add(Start);
        menu.add(Quit);
        menu.getContentPane().setBackground(Color.white);
        menu.setLayout(null);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
