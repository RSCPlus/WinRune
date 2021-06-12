package com.projectjava.winrune;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Game.Utility;

public class JCustPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7873358586692244218L;
	
	private int minWidth = Constants.BG_CONTAINER_WIDTH;
	private int minHeight = Constants.BG_CONTAINER_HEIGHT;
	
	private BufferedImage image;

    public JCustPanel() {
    	super.setBackground(Color.BLACK);
       try {                
          image = ImageIO.read(new File(Utility.findAssetDir() + "winclient_bg.png"));
       } catch (IOException ex) {
            // handle exception...
       }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = this.getSize();
        int startX = Math.max(0, (size.width - minWidth) / 2);
        int startY = Math.max(0, (size.height - minHeight) / 2);
        g.drawImage(image, startX, startY, this); // see javadoc for more info on the parameters            
    }

}
