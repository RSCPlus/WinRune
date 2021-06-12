/**
 * WinRune
 *
 * <p>This file is part of WinRune.
 *
 * <p>WinRune is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * <p>WinRune is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * <p>You should have received a copy of the GNU General Public License along with WinRune. If not,
 * see <http://www.gnu.org/licenses/>.
 *
 * <p>Authors: see <https://github.com/RSCPlus/WinRune>
 */
package com.projectjava.winrune;

import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.*;
import java.awt.Dialog.ModalityType;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;

import Game.Client;
import Game.Reflection;
import Game.Utility;

public class WinRune extends Applet implements Runnable, ComponentListener {

	private JFrame frame;
	public Applet gameApplet;
	private JEditorPane editorPane;
    private String loading, launching;
    private String client = "mudclient";
    private Map appletParams;
    private String mudclientId = "mudclient177-deob.jar";
    final String[] banner = { "attachmentwarning.gif", "banned.gif", "ccfraud.gif", "cheatwarn.gif", "dontbuysellcharacters.gif", "dontshare.gif", "gory.gif", "moremonsters.gif", "moretoexplore.gif", "moreweapons.gif", "newquests2.gif", "newskills.gif", "passadvice.gif", "wizard.gif" };
    
    private CardLayout card = new CardLayout(0,(Constants.BG_IMAGE_HEIGHT - Constants.MUD_HEIGHT) / 2 + Constants.CARD_LOCATION_OFFSET);
    private JPanel bannerPanel;
    
    public JClassLoader loader = new JClassLoader();
    
    private static WinRune instance;
    
    private static final Insets insets = new Insets(0, 0, 0, 0);
    
    public boolean members;
    private String address;
    private Integer port;
    private BigInteger rsaExponent, rsaModulus;
    
    public static WinRune getInstance() {
    	return instance;
    }

    public static void main(String[] args) throws Exception {
    	String cachedir = Utility.findCacheDir();
        String assetdir = Utility.findAssetDir();
        if (cachedir == null || assetdir == null) {
            return;
        }
    	
    	WinRune wr = new WinRune();
        instance = wr;
        
        Map params = convertToKeyValuePair(args);
        Object[] paramsArray = params.entrySet().toArray();
        for (int i = 0; i < paramsArray.length; i++) {
        	Map.Entry entry = (Entry) paramsArray[i];
        	String key = (String) entry.getKey();
        	String value = (String) entry.getValue();
        	if (key.equalsIgnoreCase("members")) {
        		instance.members = Boolean.parseBoolean(value);
        	} else if (key.equalsIgnoreCase("address")) {
        		instance.address = value;
        	} else if (key.equalsIgnoreCase("port")) {
        		instance.port = new Integer(Integer.parseInt(value));
        	} else if (key.equalsIgnoreCase("rsaExponent")) {
        		instance.rsaExponent = new BigInteger(value);
        	} else if (key.equalsIgnoreCase("rsaModulus")) {
        		instance.rsaModulus = new BigInteger(value);
        	}
        }
        
        wr.run();
    }
    
    private static Map convertToKeyValuePair(String[] args) {

        Map params = new HashMap();

        String arg;
        for (int i = 0; i < args.length; i++) {
        	arg = args[i];
            String[] splitFromEqual = arg.split("=");
            String key = splitFromEqual[0];
            String value = splitFromEqual[1];
            params.put(key, value);
        }

        return params;
    }

    public WinRune() {
    	loading = ("<html><body text=white><br><br><br><br><br><br><br><br><br><br><br><br><center><table bgcolor=black><tr><td><table bgcolor=#aaaaaa><tr><td bgcolor=black width=400><font face=Arial><center><h1>RuneScape</h1>By Jagex Software<h3>Checking for latest updates<br>Please wait a moment...</h3></center><font></td></tr></table></td></tr></table></center></body></html>");
        launching = ("<html><head></head><body text=white><br><br><br><br><br><br><br><br><br><br><br><br><center><table bgcolor=black><tr><td><table bgcolor=#aaaaaa><tr><td bgcolor=black width=400><font face=Arial><center><h1>RuneScape</h1>By Jagex Software<h3><br>Launching game...</h3></center></font></td></tr></table></td></tr></table></center></body></html>");

        ImageIcon logo = new ImageIcon(Utility.findAssetDir() + "rune.png");
        
    	frame = new JFrame("RuneScape - by Jagex Limited");
        frame.setContentPane(new JCustPanel());
        frame.setResizable(true);
        frame.addComponentListener(this);
        frame.setIconImage(logo.getImage());
        
        String win1 = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        String win2 = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
        
        try {
        	String OS = System.getProperty("os.name", "unknown").toLowerCase();
        	if (OS.contains("win")) {
        		UIManager.setLookAndFeel(win2);
        	}
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu help = new JMenu("Help");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem about = new JMenuItem("About RuneScape");
        menu.add(exit);
        help.add(about);
        menubar.add(menu);
        menubar.add(help);
        frame.setJMenuBar(menubar);
        
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitGame();
            }
        });
        
        final JDialog dialog = new JDialog(frame, "About", false);
        dialog.setLayout(new BorderLayout());
        JPanel info = new JPanel();
        info.setLayout(new GridBagLayout());
        JLabel iconLabel = new JLabel(logo);
        JLabel textLabel = new JLabel("<html>RuneScape Internet Client<br/>By Jagex Limited<br/>Copyright (C) 2001</html>");
        JButton okButton = new JButton("OK");
        Dimension buttonSize = new Dimension(75, 23);
        okButton.setSize(buttonSize);
        okButton.setPreferredSize(buttonSize);
        addComponent(info, iconLabel, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(info, textLabel, 1, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
        addComponent(info, okButton, 3, 0, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
        
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(info);
        box.add(Box.createVerticalGlue());
        dialog.add(box, BorderLayout.CENTER);
        
        okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(false);
			}
        });
        
        about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dialog.setLocationRelativeTo(frame);
				dialog.setSize(Constants.DIALOG_WIDTH, Constants.DIALOG_HEIGHT);
				dialog.setResizable(false);
			    dialog.setVisible(true);
			}
        });

        editorPane = new JEditorPane();
        editorPane.setSize(new Dimension(Constants.EDITOR_WIDTH, Constants.EDITOR_HEIGHT));
        HTMLEditorKit kit = new HTMLEditorKit();
        editorPane.setEditorKit(kit);
        editorPane.setEditable(false);
        editorPane.setText(loading);
        editorPane.setDoubleBuffered(true);

        frame.getContentPane().add(editorPane, BorderLayout.CENTER);
        editorPane.setBackground(new Color(0, 0, 0, 0));

        frame.setSize(Constants.CLIENT_WIDTH, Constants.CLIENT_HEIGHT);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentListener(this);
    }
    
    private static void addComponent(Container container, Component component, int gridx, int gridy,
    	      int gridwidth, int gridheight, int anchor, int fill) {
    	    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
    	        anchor, fill, insets, 0, 0);
    	    container.add(component, gbc);
    }
    
    public Applet getApplet() {
    	return this.gameApplet;
    }

    public void run() {
        editorPane.setText(launching);
        try {
        	load();
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, FileNotFoundException {
        String cachedir = Utility.findCacheDir();
        String jarFile = cachedir + mudclientId;
        InputStream is = new FileInputStream(jarFile);
        loader.fetch(is);
        Class clazz = loader.findClass(client);
        Reflection.Load();
        gameApplet = (Applet) clazz.newInstance();
        gameApplet.setStub(new JAppletStub());
    }
    
    private void exitGame() {
    	System.out.println("CLOSING");
    	gameApplet.stop();
        gameApplet.destroy();
        try {
            Thread.sleep(1000L);
        } catch(InterruptedException e) {
            // ignore
        }
        System.exit(0);
    }
    
    private void launch() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                exitGame();
            }
        });
        
        gameApplet.setSize(Constants.MUD_WIDTH, Constants.MUD_HEIGHT);
        int bannerIdx = (int)(Math.random() * banner.length);
        ImageIcon icon = new ImageIcon(Utility.findAssetDir() + banner[bannerIdx]);
        final JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(Constants.JAGEX_BANNER_WIDTH, Constants.BANNER_HEIGHT));
        button.setSize(Constants.JAGEX_BANNER_WIDTH, Constants.BANNER_HEIGHT);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setRolloverIcon(icon);
        ImageIcon real = new ImageIcon(Utility.findAssetDir() + "realbanner.gif");
        JButton buttonReal = new JButton(real);
        buttonReal.setPreferredSize(new Dimension(Constants.REAL_BANNER_WIDTH, Constants.BANNER_HEIGHT));
        buttonReal.setSize(Constants.REAL_BANNER_WIDTH, Constants.BANNER_HEIGHT);
        buttonReal.setBackground(Color.BLACK);
        buttonReal.setBorderPainted(false);
        buttonReal.setFocusPainted(false);
        buttonReal.setRolloverIcon(real);
        bannerPanel = new JPanel();
        bannerPanel.add(button);
        bannerPanel.add(buttonReal);
        bannerPanel.setAlignmentY(LEFT_ALIGNMENT);
        bannerPanel.setSize(Constants.MUD_WIDTH, Constants.BANNER_HEIGHT);
        gameApplet.setLayout(new BorderLayout());
        gameApplet.add(bannerPanel, BorderLayout.SOUTH);
        
        final String basePath = Utility.findAssetDir(); 
        Timer SimpleTimer = new Timer(5 * 60 * 1000, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int bannerIdx = (int)(Math.random() * banner.length);
                ImageIcon icon = new ImageIcon(basePath + banner[bannerIdx]);
                button.setIcon(icon);
                button.setRolloverIcon(icon);
            }
        });
        SimpleTimer.start();
        
        Container co = frame.getContentPane();
        frame.setLayout(card);
        co.add("game", gameApplet);
        card.next(co);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        AWTEventListener listener =
            new AWTEventListener() {
              public void eventDispatched(AWTEvent event) {
                if (event instanceof KeyEvent) {
                  KeyEvent evt = (KeyEvent) event;
                  if (evt.getID() == 401) {
                    Client.handler_keyboard.keyPressed(evt);
                  } else if (evt.getID() == 402) {
                    Client.handler_keyboard.keyReleased(evt);
                  }
                  evt.consume();
                }
              }
            };
        toolkit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);

        gameApplet.init();
        gameApplet.start();
        frame.setSize(Constants.CLIENT_WIDTH, Constants.CLIENT_HEIGHT + 2); // trigger a resize to readjust positions
        try {
			Reflection.address.set(gameApplet, address);
			Reflection.rsaExponent.set(gameApplet, rsaExponent);
			Reflection.rsaModulus.set(gameApplet, rsaModulus);
			
			Timer timer = new Timer(10000, new ActionListener() {
				  public void actionPerformed(ActionEvent arg0) {
				    // Code to be executed
					  try {
						Reflection.someBoolean.setBoolean(gameApplet, false);
						Reflection.port.set(gameApplet, port);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  }
				});
				timer.setRepeats(false); // Only execute once
				timer.start(); // Go go go!
		} catch (IllegalArgumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

	public AppletContext getAppletContext() {
		return null;
	}

	public URL getCodeBase() {
		try {
			return new URL("http://puffin/");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public String getParameter(String arg0) {
		return null;
	}

	public boolean isActive() {
		return true;
	}

    public void destroy() {
        if (gameApplet != null) {
            gameApplet.destroy();
        }
    }

    public void paint(Graphics g) {
        if (gameApplet != null) {
            gameApplet.paint(g);
        }
        if (editorPane != null) {
            editorPane.paint(g);
        }
    }

    public void update(Graphics g) {
        if (gameApplet != null) {
            gameApplet.paint(g);
        }
        if (editorPane != null) {
            editorPane.paint(g);
        }
    }

	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void componentResized(ComponentEvent e) {
		if (gameApplet == null) return;
		
		Dimension size = e.getComponent().getSize();
		gameApplet.setSize(Constants.MUD_WIDTH, Constants.MUD_HEIGHT);
		
		gameApplet.setLocation((size.width - Constants.MUD_WIDTH) / 2, (size.height - Constants.MUD_HEIGHT) / 2 + Constants.GAME_LOCATION_OFFSET);
		bannerPanel.setLocation((Constants.BG_CONTAINER_WIDTH - size.width) / 2 + Constants.BANNER_LOCATION_OFFSET_X, Constants.BANNER_LOCATION_OFFSET_Y);
	}

	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}