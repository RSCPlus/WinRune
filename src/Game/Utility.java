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

import java.io.File;

public class Utility {
	
	private static String cacheId = "cache";
	private static String assets = "assets";
	
	public static String findCacheDir() {
		return findDir(cacheId);
    }
	
	public static String findAssetDir() {
		return findDir(assets);
	}
	
	private static String findDir(String folderName) {
		File file = new File(folderName);
        if(!file.exists()) {
            file.mkdir();
        }
        return folderName + "/";
	}

}
