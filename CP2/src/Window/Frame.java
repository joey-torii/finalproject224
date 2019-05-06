package Window;

/*




*/

import javax.swing.JFrame;                                                                  

public class Frame {                                                                         
        public static void main(String[] args) {                                                 
                JFrame frame = new JFrame("");  
                menu menuObject = new menu();

                frame.getContentPane().add(new board());                       
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                               
                frame.setSize(600, 400);                                                             
                frame.setResizable(false);                                                         
                frame.setLocation(20, 50);                                                          
                frame.setVisible(true);                                                            

                //frame.add(menuObject.buildProfileButton);
                //frame.add(menuObject.name);
                
                int frameWidth  = frame.getContentPane().getWidth();
                int frameHeight  = frame.getContentPane().getHeight();
        }
}