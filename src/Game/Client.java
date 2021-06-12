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
package Game;

import java.applet.Applet;

import com.projectjava.winrune.WinRune;

public class Client {

	  public static MouseHandler handler_mouse = new MouseHandler();
	  public static KeyboardHandler handler_keyboard = new KeyboardHandler();

	  public static void init() {
	    Applet applet = WinRune.getInstance().getApplet();
	    applet.addMouseListener(handler_mouse);
	    applet.addMouseMotionListener(handler_mouse);
	    applet.addMouseWheelListener(handler_mouse);
	    applet.addKeyListener(handler_keyboard);
	    applet.setFocusTraversalKeysEnabled(false);
	  }
}
