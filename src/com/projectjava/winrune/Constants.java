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

import java.util.concurrent.atomic.AtomicReference;

public class Constants {
	
	public static final AtomicReference<Integer> CLIENT_WIDTH = new AtomicReference<Integer>(); //554;//662;
	public static final AtomicReference<Integer> CLIENT_HEIGHT = new AtomicReference<Integer>(); //515;//702;
	
	public static final int EDITOR_WIDTH = 400;
	public static final int EDITOR_HEIGHT = 500;
	
	public static final int MUD_WIDTH = 512;
	public static final AtomicReference<Integer> MUD_HEIGHT = new AtomicReference<Integer>(); //409; //346 from game + 63 of banner
	
	public static final AtomicReference<Integer> BG_IMAGE_WIDTH = new AtomicReference<Integer>(); //550;//658;
	public static final AtomicReference<Integer> BG_IMAGE_HEIGHT = new AtomicReference<Integer>(); //487;//674;
	
	public static final AtomicReference<Integer> BG_CONTAINER_WIDTH = new AtomicReference<Integer>(); //538;//646;
	public static final AtomicReference<Integer> BG_CONTAINER_HEIGHT = new AtomicReference<Integer>(); //453;//640;
	
	public static final int JAGEX_BANNER_WIDTH = 373;
	
	public static final int REAL_BANNER_WIDTH = 120; // originally 135 but button takes space from it
	
	public static final int BANNER_HEIGHT = 63;
	
	public static final int DIALOG_WIDTH = 369;
	public static final int DIALOG_HEIGHT = 123;
	
	public static final int CARD_LOCATION_OFFSET = -14;
	public static final int GAME_LOCATION_OFFSET = -28;
	
	public static final int BANNER_LOCATION_OFFSET_X = -64;
	public static final int BANNER_LOCATION_OFFSET_Y = 341;
	
	public static final AtomicReference<String> MUD_JAR = new AtomicReference<String>();
	public static final AtomicReference<String> CODE_BASE = new AtomicReference<String>();
	
	public static void initialize(int version) {
		switch(version) {
			case 2001:
				CLIENT_WIDTH.set(554);
				CLIENT_HEIGHT.set(515);
				BG_IMAGE_WIDTH.set(550);
				BG_IMAGE_HEIGHT.set(487);
				BG_CONTAINER_WIDTH.set(538);
				BG_CONTAINER_HEIGHT.set(453);
				MUD_HEIGHT.set(357);
				MUD_JAR.set("mudclient38-deob.jar");
				CODE_BASE.set("http://penguin.local/");
				break;
			default:
				CLIENT_WIDTH.set(662);
				CLIENT_HEIGHT.set(702);
				BG_IMAGE_WIDTH.set(658);
				BG_IMAGE_HEIGHT.set(674);
				BG_CONTAINER_WIDTH.set(646);
				BG_CONTAINER_HEIGHT.set(640);
				MUD_HEIGHT.set(409);
				MUD_JAR.set("mudclient177-deob.jar");
				CODE_BASE.set("http://puffin/");
				break;
		}
	}
	
}
