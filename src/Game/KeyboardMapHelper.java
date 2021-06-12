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

import java.util.HashMap;
import java.util.Map;

public class KeyboardMapHelper {

	  private static final Map map =
	      new HashMap() {
	        {
	          put(Integer.valueOf(19), Integer.valueOf(1024)); // pause/break
	          put(Integer.valueOf(20), Integer.valueOf(1022)); // caps lock
	          put(Integer.valueOf(33), Integer.valueOf(1002)); // page up
	          put(Integer.valueOf(34), Integer.valueOf(1003)); // page down
	          put(Integer.valueOf(35), Integer.valueOf(1001)); // end
	          put(Integer.valueOf(36), Integer.valueOf(1000)); // home
	          put(Integer.valueOf(37), Integer.valueOf(1006)); // left arrow
	          put(Integer.valueOf(38), Integer.valueOf(1004)); // up arrow
	          put(Integer.valueOf(39), Integer.valueOf(1007)); // right arrow
	          put(Integer.valueOf(40), Integer.valueOf(1005)); // down arrow
	          put(Integer.valueOf(112), Integer.valueOf(1008)); // f1
	          put(Integer.valueOf(113), Integer.valueOf(1009)); // f2
	          put(Integer.valueOf(114), Integer.valueOf(1010)); // f3
	          put(Integer.valueOf(115), Integer.valueOf(1011)); // f4
	          put(Integer.valueOf(116), Integer.valueOf(1012)); // f5
	          put(Integer.valueOf(117), Integer.valueOf(1013)); // f6
	          put(Integer.valueOf(118), Integer.valueOf(1014)); // f7
	          put(Integer.valueOf(119), Integer.valueOf(1015)); // f8
	          put(Integer.valueOf(120), Integer.valueOf(1016)); // f9
	          put(Integer.valueOf(121), Integer.valueOf(1017)); // f10
	          put(Integer.valueOf(122), Integer.valueOf(1018)); // f11
	          put(Integer.valueOf(123), Integer.valueOf(1019)); // f12
	          put(Integer.valueOf(144), Integer.valueOf(1023)); // num lock
	          put(Integer.valueOf(145), Integer.valueOf(1021)); // scroll lock
	        }
	      };

	  public static Integer convert(Integer modernId) {
	    return (Integer) map.getOrDefault(modernId, modernId);
	  }
	}