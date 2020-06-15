package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pole_Position extends Game {
	private SpriteBatch batch;
	private GameScreen gameScreen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this,batch);
		setScreen(gameScreen);
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
