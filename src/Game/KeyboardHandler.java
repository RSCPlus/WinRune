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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.projectjava.winrune.WinRune;

/** Listens to keyboard events to trigger specified operations */
public class KeyboardHandler implements KeyListener {

  public void keyPressed(KeyEvent e) {
    if (!e.isConsumed()) {
      try {
        Event evt = new Event(WinRune.getInstance().gameApplet, 401, e);
        Integer converted = KeyboardMapHelper.convert(Integer.valueOf(e.getKeyCode()));
        Integer n =
            Integer.valueOf(converted.intValue() == e.getKeyCode() //getExtendedKeyCode()
                ? e.getKeyChar()
                : converted.intValue()); // with getKeyCode() is always placing letter to upper
        Reflection.keyDown.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, n});
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      e.consume();
    }
  }

  public void keyReleased(KeyEvent e) {
    if (!e.isConsumed()) {
      try {
        Event evt = new Event(WinRune.getInstance().gameApplet, 402, e);
        Integer converted = KeyboardMapHelper.convert(Integer.valueOf(e.getKeyCode()));
        Integer n =
            Integer.valueOf(converted.intValue() == e.getKeyCode() //e.getExtendedKeyCode()
                ? e.getKeyChar()
                : converted.intValue()); // with getKeyCode() is always placing letter to upper
        Reflection.keyUp.invoke(WinRune.getInstance().gameApplet, new Object[]{evt, n});
      } catch (Exception e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
      e.consume();
    }
  }

  public void keyTyped(KeyEvent e) {
    System.out.println("Key typed...");
    /*if (!e.isConsumed()) {
    	e.consume();
    }*/
  }
}