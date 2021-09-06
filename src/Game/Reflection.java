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
import java.util.concurrent.atomic.AtomicReference;

import com.projectjava.winrune.JClassLoader;
import com.projectjava.winrune.WinRune;

public class Reflection {

	  private static final AtomicReference<String> GAME_SHELL = new AtomicReference<String>();
	
	  // Method descriptions
	  private static final AtomicReference<String> MOUSE_MOVE = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.mouseMove(java.awt.Event,int,int)";
	  private static final AtomicReference<String> MOUSE_DRAG = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.mouseDrag(java.awt.Event,int,int)";
	  private static final AtomicReference<String> MOUSE_UP = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.mouseUp(java.awt.Event,int,int)";
	  private static final AtomicReference<String> MOUSE_DOWN = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.mouseDown(java.awt.Event,int,int)";

	  private static final AtomicReference<String> KEY_UP = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.keyUp(java.awt.Event,int)";
	  private static final AtomicReference<String> KEY_DOWN = new AtomicReference<String>();
	      //"public synchronized boolean a.a.GameShell.keyDown(java.awt.Event,int)";
	  
	  private static final AtomicReference<String> MEMBERS = new AtomicReference<String>();
			  //"members";
	  private static final AtomicReference<String> ADDRESS = new AtomicReference<String>();
			  //"address";
	  private static final AtomicReference<String> PORT = new AtomicReference<String>();
			  //"port";
	  private static final AtomicReference<String> RSAEXPONENT = new AtomicReference<String>();
			  //"rsaExponent";
	  private static final AtomicReference<String> RSAMODULUS = new AtomicReference<String>();
			  //"rsaModulus";
	  private static final AtomicReference<String> SOMEBOOLEAN = new AtomicReference<String>();
			  //"field_8";

	  private static final AtomicReference<String> GAME_FRAME = new AtomicReference<String>();
			  //"public java.awt.Frame jagex.client.k.ij()";

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
	  
	  public static void setConstants(int version) {
		  switch(version) {
			  case 2001:
				  GAME_SHELL.set("jagex.client.k");
				  MOUSE_MOVE.set("public synchronized boolean jagex.client.k.mouseMove(java.awt.Event,int,int)");
				  MOUSE_DRAG.set("public synchronized boolean jagex.client.k.mouseDrag(java.awt.Event,int,int)");
				  MOUSE_UP.set("public synchronized boolean jagex.client.k.mouseUp(java.awt.Event,int,int)");
				  MOUSE_DOWN.set("public synchronized boolean jagex.client.k.mouseDown(java.awt.Event,int,int)");
				  KEY_UP.set("public synchronized boolean jagex.client.k.keyUp(java.awt.Event,int)");
				  KEY_DOWN.set("public synchronized boolean jagex.client.k.keyDown(java.awt.Event,int)");
				  MEMBERS.set("members");
				  ADDRESS.set("yc");
				  PORT.set("ad");
				  SOMEBOOLEAN.set("op");
				  break;
			  default:
				  GAME_SHELL.set("a.a.GameShell");
				  MOUSE_MOVE.set("public synchronized boolean a.a.GameShell.mouseMove(java.awt.Event,int,int)");
				  MOUSE_DRAG.set("public synchronized boolean a.a.GameShell.mouseDrag(java.awt.Event,int,int)");
				  MOUSE_UP.set("public synchronized boolean a.a.GameShell.mouseUp(java.awt.Event,int,int)");
				  MOUSE_DOWN.set("public synchronized boolean a.a.GameShell.mouseDown(java.awt.Event,int,int)");
				  KEY_UP.set("public synchronized boolean a.a.GameShell.keyUp(java.awt.Event,int)");
				  KEY_DOWN.set("public synchronized boolean a.a.GameShell.keyDown(java.awt.Event,int)");
				  MEMBERS.set("members");
				  ADDRESS.set("address");
				  PORT.set("port");
				  RSAEXPONENT.set("rsaExponent");
				  RSAMODULUS.set("rsaModulus");
				  SOMEBOOLEAN.set("field_8");
				  break;
		  }
	  }

	  public static void Load(int version) {
	    try {
	      JClassLoader classLoader = WinRune.getInstance().loader;
	      ArrayList leftFields =
	          new ArrayList(); // expected virtual methods to find in given class
	      
	      setConstants(version);

	      Class c = classLoader.loadClass(GAME_SHELL.get());
	      Method[] methods = c.getDeclaredMethods();
	      Method method;
	      for (int i = 0; i < methods.length; i++) {
	    	method = methods[i];
	        if (methods[i].toGenericString().equals(MOUSE_MOVE.get())) {
	          mouseMove = method;
	        } else if (method.toGenericString().equals(MOUSE_DRAG.get())) {
	          mouseDrag = method;
	        } else if (method.toGenericString().equals(MOUSE_UP.get())) {
	          mouseUp = method;
	        } else if (method.toGenericString().equals(MOUSE_DOWN.get())) {
	          mouseDown = method;
	        } else if (method.toGenericString().equals(KEY_UP.get())) {
	          keyUp = method;
	        } else if (method.toGenericString().equals(KEY_DOWN.get())) {
	          keyDown = method;
	        } else if (method.toGenericString().equals(GAME_FRAME.get())) {
	          gameFrame = method;
	        }
	      }
	      
	      c = classLoader.loadClass("mudclient");
	      leftFields.addAll(
	              Arrays.asList(new String[]{
	                  MEMBERS.get(),
	                  ADDRESS.get(),
	                  PORT.get(),
	                  RSAEXPONENT.get(),
	                  RSAMODULUS.get(),
	                  SOMEBOOLEAN.get()}));
	          while (c != null && leftFields.size() > 0) {
	        	  Field[] fields = c.getDeclaredFields();
	        	  Field field;
	    	      for (int i = 0; i < fields.length; i++) {
	    	    	  field = fields[i];
	    	    	  if (leftFields.contains(MEMBERS.get())
	    	                  && field.getName().equals(MEMBERS.get())) {
	    	                members = field;
	    	                System.out.println("Found members");
	    	                leftFields.remove(MEMBERS.get());
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(ADDRESS.get())
	    	                  && field.getName().equals(ADDRESS.get())) {
	    	                address = field;
	    	                System.out.println("Found address");
	    	                leftFields.remove(ADDRESS.get());
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(PORT.get())
	    	                  && field.getName().equals(PORT.get())) {
	    	                port = field;
	    	                System.out.println("Found port");
	    	                leftFields.remove(PORT.get());
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(RSAEXPONENT.get())
	    	                  && field.getName().equals(RSAEXPONENT.get())) {
	    	                rsaExponent = field;
	    	                System.out.println("Found rsaExponent");
	    	                leftFields.remove(RSAEXPONENT.get());
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(RSAMODULUS.get())
	    	                  && field.getName().equals(RSAMODULUS.get())) {
	    	                rsaModulus = field;
	    	                System.out.println("Found rsaModulus");
	    	                leftFields.remove(RSAMODULUS.get());
	    	                continue;
	    	          }
	    	    	  if (leftFields.contains(SOMEBOOLEAN.get())
	    	                  && field.getName().equals(SOMEBOOLEAN.get())) {
	    	                someBoolean = field;
	    	                System.out.println("Found someBoolean");
	    	                leftFields.remove(SOMEBOOLEAN.get());
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