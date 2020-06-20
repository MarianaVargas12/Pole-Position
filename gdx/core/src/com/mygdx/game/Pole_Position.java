package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import json.parser.ParseException;
import server.Server;

import java.io.IOException;

public class Pole_Position extends Game {
	public SpriteBatch batch;
	private MenuScreen menuScreen;
	private EndScreen endScreen;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);//cambiar por gameScreen
		Server server = new Server(menuScreen);
		try {
			server.startConnection("127.0.0.1",8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		server.start();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
