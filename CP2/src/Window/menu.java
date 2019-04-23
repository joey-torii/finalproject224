package Window;

//import Textures.Level1;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jetor
 */
public class menu {
    
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
                        JFrame LEVEL_ONE = new JFrame("Lv.1");
                        
                        lv1.setSize(1300,700);
                        
                        //lv1.getContentPane().setBackground(Color.white);
                        lv1.setVisible(true);
                        levels.setVisible(false);
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
                System.exit(0);
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
