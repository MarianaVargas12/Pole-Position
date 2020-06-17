package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pole_Position extends Game {
	public SpriteBatch batch;
	private MenuScreen menuScreen;
	private EndScreen endScreen;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		menuScreen = new MenuScreen(this);
		endScreen = new EndScreen(this);
		setScreen(endScreen);//cambiar por gameScreen
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
