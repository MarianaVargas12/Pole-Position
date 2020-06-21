package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class MenuScreen implements Screen {
    public Pole_Position game;
    Texture menu;
    public int carroPrincipal = -1;
    public ArrayList<Integer> CarrosDisponibles;
    public ArrayList<Integer> listaCarros = new ArrayList<>();
    public boolean start = false;
    private boolean carroSelec = true;
    public GameScreen gameScreen;

    public MenuScreen(Pole_Position game){
        this.game = game;
        menu = new Texture("core\\assets\\menu.png");
        this.CarrosDisponibles = new ArrayList<>();
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

        if(x > 120 && x < 310 && y > 185 && y < 275 && (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && carroSelec){
                if(this.CarrosDisponibles.contains(0)){
                    this.carroPrincipal = 0;
                    carroSelec = false;
                }
                else{
                    System.out.println("Error, el carro ya está elegido.");
                }
        }

        if(x > 515 && x < 650 && y > 185 && y < 287 && (Gdx.input.isButtonPressed(Input.Buttons.LEFT))&& carroSelec){
            if(this.CarrosDisponibles.contains(1)){
                    this.carroPrincipal = 1;
                    carroSelec = false;
            }
                else{
                    System.out.println("Error, el carro ya está elegido.");
                }
        }

        if(x > 131 && x < 287 && y > 308 && y < 415 && (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && carroSelec){
            if(this.CarrosDisponibles.contains(3)){
                this.carroPrincipal = 2;
                carroSelec = false;
            }
            else{
                System.out.println("Error, el carro ya está elegido.");
            }
        }

        if(x > 504 && x < 646 && y > 320 && y < 420 && (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) && carroSelec){
                if(this.CarrosDisponibles.contains(3)) {
                    this.carroPrincipal = 3;
                    carroSelec = false;
                }
                else{
                    System.out.println("Error, el carro ya está elegido.");
                }
        }

        if(x > 40 && x < 240 && y > 467 && y < 536){
            if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                if(this.carroPrincipal >= 0 && this.carroPrincipal <=3){
                    //enviar carro principal a mariana
                    this.listaCarros.add(carroPrincipal);
                    gameScreen = new GameScreen(game,new SpriteBatch(),listaCarros);
                    game.setScreen(gameScreen);
                    System.out.println("Carro Principal: " + carroPrincipal);
                    start = true;
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
