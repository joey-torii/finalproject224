package cp2;


import Window.menu;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jetor
 */
public class CP2 extends JFrame{

    public static final int WINDOW_WIDTH = 640;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH / 4 * 3;
    menu menuObject = new menu();
    
    public CP2() {
        /*
        setTitle("CRAPPY PORTAL");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        */
        menu.main(null);
    }
   
    
    public static void main(String[] args) {
 
        new CP2();
    }
    
}
