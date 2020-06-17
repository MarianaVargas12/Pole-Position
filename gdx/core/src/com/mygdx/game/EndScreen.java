package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class EndScreen implements Screen {
    Pole_Position game;
    Texture end,amarillo,negro,blanco,verde;
    ArrayList<Integer> listaCarros;
    Vector2 primero,segundo,tercero;
    int ancho,alto;
    BitmapFont fuente;

    public EndScreen(Pole_Position game){
        this.game = game;
        listaCarros = new ArrayList<>();
        listaCarros.add(1);
        listaCarros.add(2);
        listaCarros.add(3);

        primero = new Vector2(335,510);
        segundo = new Vector2(110,430);
        tercero = new Vector2(580,400);

        fuente = new BitmapFont();
        fuente.setColor(Color.BLACK);
        fuente.getData().setScale((2f));

        ancho = 100;
        alto = 75;

        end = new Texture("core\\assets\\End.png");
        amarillo = new Texture("core\\assets\\Amarillo.png");
        negro =  new Texture("core\\assets\\Negro.png");
        verde =  new Texture("core\\assets\\Verde.png");
        blanco =  new Texture("core\\assets\\Blanco.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(end,0,0,GameScreen.GAME_WIDTH*3,GameScreen.GAME_HEIGHT*3);
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();


        game.batch.draw(verde,primero.x,primero.y,ancho,alto);
        game.batch.draw(blanco,segundo.x,segundo.y,ancho,alto);
        game.batch.draw(amarillo,tercero.x,tercero.y,ancho,alto);

        fuente.draw(game.batch,"10000pts",325,450);
        fuente.draw(game.batch,"9000pts",100,370);
        fuente.draw(game.batch,"3000pts",570,350);

        if (x > 572 && x < 754 && y > 615 && y < 667){
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
