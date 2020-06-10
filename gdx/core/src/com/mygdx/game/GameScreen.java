package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameScreen extends ScreenAdapter {
    public static final int GAME_HEIGHT = 224;
    public static final int GAME_WIDTH = 256;

    private Game game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private PixMap3D pixmap;

    public GameScreen(Game game, SpriteBatch batch){
        this.game = game;
        this.batch = batch;
    }

    @Override
    public void show(){
        camera = new OrthographicCamera(GAME_WIDTH,GAME_HEIGHT);//tamano de la camara
        camera.position.set(GAME_WIDTH/2,GAME_HEIGHT/2,0);//posicion de la camara en la mitad de la pantalla
        camera.update();

        pixmap = new PixMap3D(GAME_WIDTH,GAME_HEIGHT, Pixmap.Format.RGB565);//crea el efecto de baja resolucion
    }

    @Override
    public void render(float delta){
        //limpia la pantalla con el color negro
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        handleInput();//maneja los botones presionados
        camera.update();//actualiza la camara

        batch.begin();
        pixmap.render(batch);//renderiza todo lo que haya en el pixmap
        batch.end();
    }
    private void handleInput(){

    }

    @Override
    public void dispose(){

    }

}
