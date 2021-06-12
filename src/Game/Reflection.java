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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import com.projectjava.winrune.JClassLoader;
import com.projectjava.winrune.WinRune;

public class Reflection {

	  // Method descriptions
	  private static final String MOUSE_MOVE =
	      "public synchronized boolean a.a.GameShell.mouseMove(java.awt.Event,int,int)";
	  private static final String MOUSE_DRAG =
	      "public synchronized boolean a.a.GameShell.mouseDrag(java.awt.Event,int,int)";
	  private static final String MOUSE_UP =
	      "public synchronized boolean a.a.GameShell.mouseUp(java.awt.Event,int,int)";
	  private static final String MOUSE_DOWN =
	      "public synchronized boolean a.a.GameShell.mouseDown(java.awt.Event,int,int)";

	  private static final String KEY_UP =
	      "public synchronized boolean a.a.GameShell.keyUp(java.awt.Event,int)";
	  private static final String KEY_DOWN =
	      "public synchronized boolean a.a.GameShell.keyDown(java.awt.Event,int)";
	  
	  private static final String MEMBERS = "members";
	  private static final String ADDRESS = "address";
	  private static final String PORT = "port";
	  private static final String RSAEXPONENT = "rsaExponent";
	  private static final String RSAMODULUS = "rsaModulus";
	  private static final String SOMEBOOLEAN = "field_8";

	  private static final String GAME_FRAME = "public java.awt.Frame jagex.client.k.ij()";

	  public static Method mouseMove = null;
	  public static Method mouseDrag = null;
	  public static Method mouseUp = null;
	  public static Method mouseDown = null;
	  public static Method keyUp = null;
	  public static Method keyDown = null;
	  public static Method gameFrame = null;
	  
	  public static Field members = null;
	  public static Field address = null;
	  public static Field port = null;
	  public static Field rsaExponent = null;
	  public static Field rsaModulus = null;
	  public static Field someBoolean = null;

	  public static void Load() {
	    try {
	      JClassLoader classLoader = WinRune.getInstance().loader;
	      ArrayList leftFields =
	          new ArrayList(); // expected virtual methods to find in given class

	      Class c = classLoader.loadClass("a.a.GameShell");
	      Method[] methods = c.getDeclaredMethods();
	      Method method;
	      for (int i = 0; i < methods.length; i++) {
	    	method = methods[i];
	        if (methods[i].toGenericString().equals(MOUSE_MOVE)) {
	          mouseMove = method;
	        } else if (method.toGenericString().equals(MOUSE_DRAG)) {
	          mouseDrag = method;
	        } else if (method.toGenericString().equals(MOUSE_UP)) {
	          mouseUp = method;
	        } else if (method.toGenericString().equals(MOUSE_DOWN)) {
	          mouseDown = method;
	        } else if (method.toGenericString().equals(KEY_UP)) {
	          keyUp = method;
	        } else if (method.toGenericString().equals(KEY_DOWN)) {
	          keyDown = method;
	        } else if (method.toGenericString().equals(GAME_FRAME)) {
	          gameFrame = method;
	        }
	      }
	      
	      c = classLoader.loadClass("mudclient");
	      leftFields.addAll(
	              Arrays.asList(new String[]{
	                  MEMBERS,
	                  ADDRESS,
	                  PORT,
	                  RSAEXPONENT,
	                  RSAMODULUS,
	                  SOMEBOOLEAN}));
	          while (c != null && leftFields.size() > 0) {
	        	  Field[] fields = c.getDeclaredFields();
	        	  Field field;
	    	      for (int i = 0; i < fields.length; i++) {
	    	    	  field = fields[i];
	    	    	  if (leftFields.contains(MEMBERS)
	    	                  && field.getName().equals(MEMBERS)) {
	    	                members = field;
	    	                System.out.println("Found members");
	    	                leftFields.remove(MEMBERS);
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(ADDRESS)
	    	                  && field.getName().equals(ADDRESS)) {
	    	                address = field;
	    	                System.out.println("Found address");
	    	                leftFields.remove(ADDRESS);
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(PORT)
	    	                  && field.getName().equals(PORT)) {
	    	                port = field;
	    	                System.out.println("Found port");
	    	                leftFields.remove(PORT);
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(RSAEXPONENT)
	    	                  && field.getName().equals(RSAEXPONENT)) {
	    	                rsaExponent = field;
	    	                System.out.println("Found rsaExponent");
	    	                leftFields.remove(RSAEXPONENT);
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(RSAMODULUS)
	    	                  && field.getName().equals(RSAMODULUS)) {
	    	                rsaModulus = field;
	    	                System.out.println("Found rsaModulus");
	    	                leftFields.remove(RSAMODULUS);
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(SOMEBOOLEAN)
	    	                  && field.getName().equals(SOMEBOOLEAN)) {
	    	                someBoolean = field;
	    	                System.out.println("Found someBoolean");
	    	                leftFields.remove(SOMEBOOLEAN);
	    	                continue;
	    	          }
	    	      }
	    	      c = c.getSuperclass();
	          }

	      // Set all accessible
	      if (mouseMove != null) mouseMove.setAccessible(true);
	      if (mouseDrag != null) mouseDrag.setAccessible(true);
	      if (mouseUp != null) mouseUp.setAccessible(true);
	      if (mouseDown != null) mouseDown.setAccessible(true);
	      if (keyUp != null) keyUp.setAccessible(true);
	      if (keyDown != null) keyDown.setAccessible(true);
	      if (gameFrame != null) gameFrame.setAccessible(true);
	      if (address != null) address.setAccessible(true);
	      if (someBoolean != null) someBoolean.setAccessible(true);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	}