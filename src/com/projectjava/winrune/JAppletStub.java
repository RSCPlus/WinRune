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

import java.applet.AppletContext;
import java.applet.AppletStub;
import java.net.MalformedURLException;
import java.net.URL;

public class JAppletStub implements AppletStub {

	public void appletResize(int arg0, int arg1) { }

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
		System.out.println("Paramater requested: " + arg0);
		if (arg0.equals("member")) {
			return WinRune.getInstance().members ? "1" : "0";
		}
		return null;
	}

	public boolean isActive() {
		return true;
	}

}
