package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MenuScreen implements Screen {
    Pole_Position game;
    Texture menu;
    ArrayList<Integer> listaCarros = new ArrayList<>();
    public int carroPrincipal;

    public MenuScreen(Pole_Position game){
        this.game = game;
        menu = new Texture("core\\assets\\menu.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(menu,0,0,GameScreen.GAME_WIDTH*3,GameScreen.GAME_HEIGHT*3);
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        if(x > 120 && x < 310 && y > 185 && y < 275){
            if(Gdx.input.isTouched()){
                this.carroPrincipal = 0;
            }
        }

        if(x > 515 && x < 650 && y > 185 && y < 287){
            if(Gdx.input.isTouched()){
                this.carroPrincipal = 1;
            }
        }

        if(x > 131 && x < 287 && y > 308 && y < 415){
            if(Gdx.input.isTouched()){
                this.carroPrincipal = 2;
            }
        }

        if(x > 504 && x < 646 && y > 320 && y < 420){
            if(Gdx.input.isTouched()){
                this.carroPrincipal = 3;
            }
        }

        if(x > 40 && x < 240 && y > 467 && y < 536){
            if(Gdx.input.isTouched()){
                if(this.carroPrincipal >= 0 && this.carroPrincipal <=3){
                    listaCarros.add(carroPrincipal);
                    if (this.carroPrincipal == 0){
                        listaCarros.add(1);
                        listaCarros.add(2);
                        listaCarros.add(3);
                    }
                    else if(this.carroPrincipal == 1){
                        listaCarros.add(0);
                        listaCarros.add(2);
                        listaCarros.add(3);
                    }
                    else if (this.carroPrincipal == 2){
                        listaCarros.add(0);
                        listaCarros.add(1);
                        listaCarros.add(3);
                    }
                    else {
                        listaCarros.add(0);
                        listaCarros.add(1);
                        listaCarros.add(2);
                    }
                    game.setScreen(new GameScreen(game,new SpriteBatch()));
                    System.out.println("Carro Principal: " + carroPrincipal);
                }

            }
        }

        if(x > 528 && x < 733 && y > 466 && y < 540){
            if(Gdx.input.isTouched()){
                System.out.println("Juego Finalizado");
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
