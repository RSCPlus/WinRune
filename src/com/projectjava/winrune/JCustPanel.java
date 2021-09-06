package com.projectjava.winrune;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
	
	private int minWidth;
	private int minHeight;
	private int gameVersion;
	
	private BufferedImage image;

    public JCustPanel(int version) {
    	super.setBackground(Color.BLACK);
    	minWidth = Constants.BG_CONTAINER_WIDTH.get();
    	minHeight = Constants.BG_CONTAINER_HEIGHT.get();
    	gameVersion = version;
    	try {
    		if (gameVersion != 2001) {
    			image = ImageIO.read(new File(Utility.findAssetDir() + "winclient_bg.png"));
        	} else {
        		image = ImageIO.read(new File(Utility.findAssetDir() + "winclient_bg2001.png"));
        	}
         } catch (IOException ex) {
              // handle exception...
         }       
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension size = this.getSize();
        int startX = Math.max(0, (size.width - minWidth) / 2);
        int startY = Math.max(0, (size.height - minHeight) / 2);
        if (gameVersion != 2001) {
        	 
        } else {
        	Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            int w = getWidth();
            int h = getHeight();
            Color color1 = Color.BLACK;
            Color color2 = Color.GRAY;
            GradientPaint gp = new GradientPaint(0, 0, color1, w, 0, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);
        }
        g.drawImage(image, startX, startY, this); // see javadoc for more info on the parameters
    }

}
