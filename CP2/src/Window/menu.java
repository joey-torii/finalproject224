package Window;

/*

*/



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class menu {
    public static Rectangle playButton = new Rectangle(250, 200, 100, 37);
    public static Rectangle quitButton = new Rectangle(250, 250, 100, 37);
    public static Rectangle profile = new Rectangle(470,40,100, 37);
    //final JButton Profile = new JButton(new ImageIcon("profile.PNG"));
    private JButton profileButton;
    
    
    public static void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        Font fnt0 = new Font("arial", Font.BOLD, 45);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        Font fnt2 = new Font("arial", Font.BOLD, 15);
        g.setFont(fnt0);
        g.setColor(Color.black);
        g.drawString("Crappy Portal", 150, 100);
       
        g.setFont(fnt1);
        g.drawString("Play", playButton.x + 20, playButton.y + 30);
            g2d.draw(playButton);
        g.drawString("Quit", quitButton.x + 20, quitButton.y + 30);
            g2d.draw(quitButton);
        
        g.setFont(fnt2);
        g.drawString("Profile", 470, 30);
    }
    /*
    public static void main(String [] ags){
        Profile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String profile;
                JLabel name;

                profile = JOptionPane.showInputDialog("Profile");
                name = new JLabel(profile);
                name.setFont(new Font("Arial Black", Font.BOLD, 36));
                menu.add(name);
                name.setBounds(1100, 20, 160, 50);
                Profile.setVisible(false);
            }
        });
    }*/
    
    public menu(){
        
        
    }
    
    public void buildProfileButton(){
        
        profileButton = new JButton("Profile");
        
        profileButton.addActionListener(new profileButtonListener());
        
    }
    
    private class profileButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JLabel name;
            String profile;
            
            profile = JOptionPane.showInputDialog("Profile");
            name = new JLabel(profile);
            name.setFont(new Font("Arial Black", Font.BOLD, 36));
            //menu.add(name);
            name.setBounds(1100, 20, 160, 50);
            //Profile.setVisible(false);
            
        }
    }
}