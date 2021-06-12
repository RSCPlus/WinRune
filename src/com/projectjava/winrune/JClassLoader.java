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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/** Deals with fetching and loading RSC jar. */
public class JClassLoader extends ClassLoader {

  /** Stores class names and the corresponding class byte data */
  private Map m_classData = new HashMap();

  /**
   * Fetches the game jar and loads and patches the classes
   *
   * @param jarURL The URL of the jar to be loaded and patched
   * @return If no exceptions occurred
   */
  public boolean fetch(InputStream is) {

    try {
      JarInputStream in = new JarInputStream(is);

      JarEntry entry;
      while ((entry = in.getNextJarEntry()) != null) {
        // Check if file is needed
        String name = entry.getName();

        // Read class to byte array
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int readSize;
        while ((readSize = in.read(data, 0, data.length)) != -1) bOut.write(data, 0, readSize);
        byte[] classData = bOut.toByteArray();
        bOut.close();

        if (name.endsWith(".class")) {
          name = name.replace(".class", "").replace('/', '.');
          m_classData.put(name, classData);
        }
      }
      in.close();
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public final Class findClass(String name) {
    byte[] data = (byte[]) m_classData.get(name);
    if (data == null) return null;

    return defineClass(name, data, 0, data.length);
  }
}
