package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entidades.hole;


public class GameScreen extends ScreenAdapter {
    public static final int GAME_HEIGHT = 224;
    public static final int GAME_WIDTH = 256;
    public static final double TURN_ANGLE = 0.02;

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
        dispose();

        batch.setProjectionMatrix(camera.combined);
        handleInput(delta);//maneja los botones presionados
        camera.update();//actualiza la camara

        batch.begin();
        pixmap.render(batch);//renderiza todo lo que haya en el pixmap
        batch.end();
    }
    //manejo de teclas
    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            //llamar al servidor para la velocidad
            pixmap.pos.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
            pixmap.pos.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
            pixmap.objects.get(0).position.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
            pixmap.objects.get(0).position.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            pixmap.pos.x -= 2 * Math.cos(pixmap.angle);
            pixmap.pos.y -= 2 * Math.sin(pixmap.angle);
            pixmap.objects.get(0).position.x -= 2 * Math.cos(pixmap.angle);
            pixmap.objects.get(0).position.y -= 2 * Math.sin(pixmap.angle);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            pixmap.objects.get(1).position.x += pixmap.carroPrincipal.velocidad * Math.cos(pixmap.angle);
            pixmap.objects.get(1).position.y += pixmap.carroPrincipal.velocidad * Math.sin(pixmap.angle);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            pixmap.objects.get(1).position.x += 2 * Math.cos(pixmap.angle - 1.5);
            pixmap.objects.get(1).position.y += 2 * Math.sin(pixmap.angle - 1.5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            pixmap.objects.get(1).position.x += 2 * Math.cos(pixmap.angle + 1.5);
            pixmap.objects.get(1).position.y += 2 * Math.sin(pixmap.angle + 1.5);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            pixmap.pos.x += 2 * Math.cos(pixmap.angle - 1.5);
            pixmap.pos.y += 2 * Math.sin(pixmap.angle - 1.5);
            pixmap.objects.get(0).position.x += 2 * Math.cos(pixmap.angle - 1.5);
            pixmap.objects.get(0).position.y += 2 * Math.sin(pixmap.angle - 1.5);

        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            pixmap.pos.x += 2 * Math.cos(pixmap.angle + 1.5);
            pixmap.pos.y += 2 * Math.sin(pixmap.angle + 1.5);
            pixmap.objects.get(0).position.x += 2 * Math.cos(pixmap.angle + 1.5);
            pixmap.objects.get(0).position.y += 2 * Math.sin(pixmap.angle + 1.5);

        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            pixmap.angle -= TURN_ANGLE;
            pixmap.backgroundPos += 0.5f;
            pixmap.objects.get(0).position.x = (float)(72*Math.cos(pixmap.angle)) + pixmap.pos.x;
            pixmap.objects.get(0).position.y = (float)(72*Math.sin(pixmap.angle)) + pixmap.pos.y;
            pixmap.pos.x += 2 * Math.cos(pixmap.angle + 1.5);
            pixmap.pos.y += 2 * Math.sin(pixmap.angle + 1.5);
            pixmap.objects.get(0).position.x += 2 * Math.cos(pixmap.angle + 1.5);
            pixmap.objects.get(0).position.y += 2 * Math.sin(pixmap.angle + 1.5);

            //acomoda la imagen del fondo
            if (pixmap.backgroundPos >= 0) {
                pixmap.backgroundPos = -256;
            }

        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            pixmap.angle += TURN_ANGLE;
            pixmap.backgroundPos -= 0.5f;
            pixmap.objects.get(0).position.x = (float) (72*Math.cos(pixmap.angle))+pixmap.pos.x;
            pixmap.objects.get(0).position.y = (float)(72*Math.sin(pixmap.angle))+pixmap.pos.y;
            pixmap.pos.x += 2 * Math.cos(pixmap.angle - 1.5);
            pixmap.pos.y += 2 * Math.sin(pixmap.angle - 1.5);
            pixmap.objects.get(0).position.x += 2 * Math.cos(pixmap.angle - 1.5);
            pixmap.objects.get(0).position.y += 2 * Math.sin(pixmap.angle - 1.5);

            if (pixmap.backgroundPos <= -512) {//acomoda la imagen del fondo
                pixmap.backgroundPos = -256;
            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            int x = (int)pixmap.objects.get(0).position.x;
            int y = (int)pixmap.objects.get(0).position.y + 100;
            hole hueco = new hole(x ,y - 300);
            pixmap.objects.add(hueco.sprite);
        }
    }

    @Override
    public void dispose(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}
