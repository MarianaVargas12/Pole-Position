package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.GameScreen;
import com.mygdx.game.Pole_Position;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		//tamano de la ventana en pantalla
		config.width = GameScreen.GAME_WIDTH * 3;
		config.height = GameScreen.GAME_HEIGHT * 3;
		config.resizable = false;
		new LwjglApplication(new Pole_Position(), config);
	}
}
