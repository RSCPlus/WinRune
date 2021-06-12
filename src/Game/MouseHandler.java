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

import java.awt.Event;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import com.projectjava.winrune.WinRune;

public class MouseHandler implements MouseListener, MouseMotionListener, MouseWheelListener {

	  public static Integer x = Integer.valueOf(0);
	  public static Integer y = Integer.valueOf(0);
	  public static boolean mouseClicked = false;
	  public static boolean rightClick = false;

	  public static boolean inBounds(Rectangle bounds) {
	    if (bounds == null) return false;
	    return false;
	  }

	  public boolean inConsumableButton() {
	    return false;
	  }

	  public void mouseClicked(MouseEvent e) {
	    if (inConsumableButton()) {
	      e.consume();
	    }

	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      e.consume();
	    }
	  }

	  public void mouseEntered(MouseEvent e) {
	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      e.consume();
	    }
	  }

	  public void mouseExited(MouseEvent e) {
	    if (!e.isConsumed()) {
	      x = Integer.valueOf(-100);
	      y = Integer.valueOf(-100);
	      e.consume();
	    }
	  }

	  public void mousePressed(MouseEvent e) {
	    if (inConsumableButton()) {
	      e.consume();
	    }

	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      try {
	        Event evt =
	            new Event(
	                WinRune.getInstance().gameApplet, e.getWhen(), 501, e.getX(), e.getY(), 0, e.getModifiers(), e);
	        Reflection.mouseDown.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, x, y});
	      } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	      e.consume();
	    }

	    mouseClicked = true;
	    rightClick = SwingUtilities.isRightMouseButton(e);
	  }

	  public void mouseReleased(MouseEvent e) {
	    if (inConsumableButton()) {
	      e.consume();
	    }

	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      try {
	        Event evt =
	            new Event(
	                WinRune.getInstance().gameApplet, e.getWhen(), 502, e.getX(), e.getY(), 0, e.getModifiers(), e);
	        Reflection.mouseUp.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, x, y});
	      } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	      e.consume();
	    }
	  }

	  public void mouseDragged(MouseEvent e) {
	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      try {
	        Event evt = new Event(WinRune.getInstance().gameApplet, 506, e);
	        Reflection.mouseDrag.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, x, y});
	      } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	      e.consume();
	    }
	  }

	  public void mouseMoved(MouseEvent e) {
	    if (!e.isConsumed()) {
	      x = Integer.valueOf(e.getX());
	      y = Integer.valueOf(e.getY());
	      try {
	        Event evt = new Event(WinRune.getInstance().gameApplet, 503, e);
	        Reflection.mouseMove.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, x, y});
	      } catch (Exception e1) {
	        // TODO Auto-generated catch block
	        e1.printStackTrace();
	      }
	      e.consume();
	    }
	  }

	  public void mouseWheelMoved(MouseWheelEvent e) {
	    x = Integer.valueOf(e.getX());
	    y = Integer.valueOf(e.getY());
	    e.consume();
	  }
	}
