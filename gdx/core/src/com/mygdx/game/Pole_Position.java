package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pole_Position extends Game {
	public SpriteBatch batch;
	private GameScreen gameScreen;
	private MenuScreen menuScreen;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameScreen = new GameScreen(this,batch);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);//cambiar por gameScreen
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
