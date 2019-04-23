/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.Object.*;
import javax.swing.JFrame;
/**
 *
 * Building a screen manager
 */
public class ScreenManager {    
        //use a GraphicsDevice to help cohesively build graphics
        private GraphicsDevice device;
        
        public ScreenManager(){
            GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            device = environment.getDefaultScreenDevice(); 
            DisplayMode displayMode = device.getDisplayMode();
        }
        
            
        public DisplayMode[] getCompatibleDisplayModes() 
        {
            return device.getDisplayModes();
        }



        public DisplayMode findFirstCompatibleMode(DisplayMode modes[])
        {
            DisplayMode goodModes[] = device.getDisplayModes();
            for (int i = 0; i < modes.length; i++) 
            {
                for (int j = 0; j < goodModes.length; j++) 
                {
                    if (displayModesMatch(modes[i], goodModes[j])) 
                    {
                        return modes[i];
                    }
                }
            }
            return null;
        }


        public DisplayMode getCurrentDisplayMode() 
        {
            return device.getDisplayMode();
        }

        public boolean displayModesMatch(DisplayMode mode1,DisplayMode mode2)
        {
            if (mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight())
            {
                return false;
            }

            if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
                mode1.getBitDepth() != mode2.getBitDepth())
            {
                return false;
            }

            if (mode1.getRefreshRate() !=
                DisplayMode.REFRESH_RATE_UNKNOWN &&
                mode2.getRefreshRate() !=
                DisplayMode.REFRESH_RATE_UNKNOWN &&
                mode1.getRefreshRate() != mode2.getRefreshRate())
             {
                 return false;
             }

             return true;
        }

        //once the button for the level selection is hit
        //this will make it full screen
        public void setFullScreen(DisplayMode displayMode) throws InvocationTargetException{
            final JFrame level = new JFrame();
            level.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            level.setResizable(false);
            
            device.setFullScreenWindow(level);
            
            try{
                device.setDisplayMode(displayMode);
            } catch (IllegalArgumentException e){}
            
            level.setSize(displayMode.getWidth(), displayMode.getHeight());
            try
            {
                EventQueue.invokeAndWait(new Runnable()
                {
                    @Override
                    public void run(){
                        level.createBufferStrategy(2);
                    }            
                });
                
            } catch (InterruptedException e){}
        }
        
        //gets the draw graphics
        public Graphics2D getGraphics() {
            Window window = device.getFullScreenWindow();
            if (window != null){
                BufferStrategy strategy = window.getBufferStrategy();
                return (Graphics2D)strategy.getDrawGraphics();
            }
            return null;
        }
        
        //updates the screen
        public void update(){
            Window window = device.getFullScreenWindow();
            if(window != null) {
                BufferStrategy strategy = window.getBufferStrategy();
                if (!strategy.contentsLost()){
                    strategy.show();
                }
            }
            
            Toolkit.getDefaultToolkit().sync();
        }
        
        //finds the size of the full window
        public JFrame getFullScreenWindow(){
            return (JFrame)device.getFullScreenWindow();
        }
        
        //gets window width
        public int getWidth() {
            Window window = device.getFullScreenWindow();
            if (window != null) {
                return window.getWidth();
            } else {
                return 0;
            }
        }
        
        //gets the window height
        public int getHeight() {
            Window window = device.getFullScreenWindow();
            if (window != null) {
                return window.getHeight();
            }else {
                return 0;
            }
        }


    
        public void restoreScreen() {
            Window window = device.getFullScreenWindow();
            if (window != null) {
                window.dispose();
            }
            device.setFullScreenWindow(null);
        }


        public BufferedImage createCompatibleImage(int w, int h,
            int transparancy) {
            Window window = device.getFullScreenWindow();
            if (window != null) {
                GraphicsConfiguration gc =
                    window.getGraphicsConfiguration();
                return gc.createCompatibleImage(w, h, transparancy);
            }
            return null;
        }
}