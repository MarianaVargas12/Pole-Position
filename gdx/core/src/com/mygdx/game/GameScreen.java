package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import entidades.Boost;
import entidades.Hole;
import entidades.Misil;
import entidades.Vida;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {
    public static final int GAME_HEIGHT = 224;
    public static final int GAME_WIDTH = 256;
    public static final double TURN_ANGLE = 0.02;

    private Game game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    public PixMap3D pixmap;
    public boolean gameStart = false;
    public boolean end = false;
    private ArrayList<Integer> listaCarros;
    public ArrayList<Vector2> podio;
    public EndScreen endScreen;

    public GameScreen(Game game, SpriteBatch batch, ArrayList carros){
        this.game = game;
        this.batch = batch;
        this.listaCarros = carros;
    }

    @Override
    public void show(){
        camera = new OrthographicCamera(GAME_WIDTH,GAME_HEIGHT);//tamano de la camara
        camera.position.set(GAME_WIDTH/2,GAME_HEIGHT/2,0);//posicion de la camara en la mitad de la pantalla
        camera.update();
        pixmap = new PixMap3D(GAME_WIDTH,GAME_HEIGHT, Pixmap.Format.RGB565,listaCarros);//crea el efecto de baja resolucion
    }

    @Override
    public void render(float delta){
            //limpia la pantalla con el color negro
            dispose();

            batch.setProjectionMatrix(camera.combined);
            handleInput(delta);//maneja los botones presionados
            camera.update();//actualiza la camara

            batch.begin();
            pixmap.render(batch);//renderiza lo que haya en el pixmap
            batch.end();
            if (end == true){
                endScreen = new EndScreen((Pole_Position) game,podio);
                game.setScreen(endScreen);
            }

    }
    //manejo de teclas
    private void handleInput(float delta) {
        if(gameStart) {


            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                //llamar al servidor para la velocidad
                pixmap.pos.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
                pixmap.pos.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
                pixmap.objects.get(0).position.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
                pixmap.objects.get(0).position.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);

            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                pixmap.pos.x -= pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
                pixmap.pos.y -= pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
                pixmap.objects.get(0).position.x -= pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
                pixmap.objects.get(0).position.y -= pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
            }


            if (Gdx.input.isKeyPressed(Input.Keys.A) &&  (Gdx.input.isKeyPressed(Input.Keys.W) ||  Gdx.input.isKeyPressed(Input.Keys.S))) {
                pixmap.angle -= TURN_ANGLE;
                pixmap.backgroundPos += 0.5f;
                pixmap.objects.get(0).position.x = (float) (72 * Math.cos(pixmap.angle)) + pixmap.pos.x;
                pixmap.objects.get(0).position.y = (float) (72 * Math.sin(pixmap.angle)) + pixmap.pos.y;
                pixmap.pos.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle + 1.5);
                pixmap.pos.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle + 1.5);

                //acomoda la imagen del fondo
                if (pixmap.backgroundPos >= 0) {
                    pixmap.backgroundPos = -256;
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) && (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.S) ) ) {
                pixmap.angle += TURN_ANGLE;
                pixmap.backgroundPos -= 0.5f;
                pixmap.objects.get(0).position.x = (float) (72 * Math.cos(pixmap.angle)) + pixmap.pos.x;
                pixmap.objects.get(0).position.y = (float) (72 * Math.sin(pixmap.angle)) + pixmap.pos.y;
                pixmap.pos.x += 2 * Math.cos(pixmap.angle - 1.5);
                pixmap.pos.y += 2 * Math.sin(pixmap.angle - 1.5);

                if (pixmap.backgroundPos <= -512) {//acomoda la imagen del fondo
                    pixmap.backgroundPos = -256;
                }

            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.H)) {
                int x = (int) pixmap.objects.get(0).position.x;
                int y = (int) pixmap.objects.get(0).position.y;

                //pasar coordenadas donde genero el hueco
                Hole hueco = new Hole(x, y);
                pixmap.objects.add(hueco.sprite);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.B)) {
                int x = (int) pixmap.objects.get(0).position.x;
                int y = (int) pixmap.objects.get(0).position.y + 100;
                Boost turbo = new Boost(x, y - 300);
                pixmap.objects.add(turbo.sprite);
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                int x = (int) pixmap.objects.get(0).position.x;
                int y = (int) pixmap.objects.get(0).position.y;
                Misil bomb = new Misil(x, y);
                pixmap.objects.add(bomb.sprite);
                pixmap.carros.get(0).bombas.add(bomb);
                pixmap.carros.get(0).bomb = true;
            }
        }
    }

    @Override
    public void dispose(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
