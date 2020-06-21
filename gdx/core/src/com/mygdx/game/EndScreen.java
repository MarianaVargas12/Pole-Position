package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class EndScreen implements Screen {
    Pole_Position game;
    Texture end,amarillo,negro,blanco,verde;
    ArrayList<Vector2> carros,posiciones,coordenadas;
    Vector2 primero,segundo,tercero,pos1,pos2,pos3;
    int ancho,alto;
    BitmapFont fuente;
    public EndScreen(Pole_Position game,ArrayList<Vector2> listaCarros){
        this.game = game;
        this.carros = listaCarros;
        this.pos1 = new Vector2();
        this.pos2 = new Vector2();
        this.pos3 = new Vector2();
        this.posiciones = new ArrayList<>();
        this.coordenadas = new ArrayList<>();
        this.fuente = new BitmapFont();
        int mayor = 0;
        int medio = 0;
        int menor = 0;

        for(Vector2 carro : carros){
           if (carro.x > mayor){
               mayor = (int) carro.x;
               pos1.x = mayor;
               pos1.y = (int) carro.y;
           }
        }
        for(Vector2 carro : carros){
            if (carro.x < mayor && carros.get(0).x > medio){
                medio = (int) carro.x;
                pos2.x = medio;
                pos2.y = (int) carro.y;
            }
        }
        for(Vector2 carro : carros){
            if (carro.x < medio && carros.get(0).x > menor){
                menor = (int) carro.x;
                pos3.x = menor;
                pos3.y = (int) carro.y;
            }
        }

        posiciones.add(pos1);
        posiciones.add(pos2);
        posiciones.add(pos3);

        primero = new Vector2(335,510);
        segundo = new Vector2(110,430);
        tercero = new Vector2(580,400);

        coordenadas.add(primero);
        coordenadas.add(segundo);
        coordenadas.add(tercero);


        fuente.setColor(Color.BLACK);
        fuente.getData().setScale((2f));

        ancho = 100;
        alto = 75;

        end = new Texture("core\\assets\\End.png");
    }
    @Override
    public void show() {

    }
    public Texture dibujar(int carro){
        amarillo = new Texture("core\\assets\\Amarillo.png");
        negro =  new Texture("core\\assets\\Negro.png");
        verde =  new Texture("core\\assets\\Verde.png");
        blanco =  new Texture("core\\assets\\Blanco.png");
        if(carro == 0 ){
            return negro;
        }
        else if(carro == 1 ){
            return verde;
        }
        else if(carro == 2 ){
            return blanco;
        }
        else{
            return amarillo;
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(end,0,0,GameScreen.GAME_WIDTH*3,GameScreen.GAME_HEIGHT*3);
        int x = Gdx.input.getX();
        int y = Gdx.input.getY();

        for(int i = 0; i<this.carros.size(); i++){
            game.batch.draw(dibujar((int) posiciones.get(i).y),coordenadas.get(i).x,coordenadas.get(i).y,ancho,alto);
        }

        fuente.draw(game.batch, pos1.x + "pts",325,450);
        fuente.draw(game.batch,pos2.x + "pts",100,370);
        fuente.draw(game.batch,pos3.x + "pts",570,350);

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
