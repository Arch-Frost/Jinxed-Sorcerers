package com.mygdx.cic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(720, 480);
		config.setForegroundFPS(60);
		config.setTitle("CIC");
		config.setMaximized(true);
		config.setResizable(false);
		//config.setFullscreenMode();
		new Lwjgl3Application(new CIC(), config);
	}
}
