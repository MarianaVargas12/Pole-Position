package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

public class PixMap3D extends Pixmap {
    private Texture background;
    public float backgroundPos;
    private Texture pixmapTexture;
    private int horizon;
    public double angle;
    private Pixmap grass; //informacion del pasto en un arreglo 2D
    private Pixmap track;
    public Vector3 pos;
    public Vector3 scale;
    public Vector2 escalaEntidad;

    public ArrayList<Sprite3D> entidades;

    public PixMap3D (int width, int height, Format format){
        super(width,height,format);
        setFilter(Filter.NearestNeighbour);//modo en el que coloca los pixeles


        pixmapTexture = new Texture(this,getFormat(),true);
        horizon = 50;//distancia en pixeles a partir de la parte superior de la pantalla
        grass = new Pixmap(new FileHandle("core\\assets\\zacate.png"));
        track = new Pixmap(new FileHandle("core\\assets\\pista.png"));
        pos = new Vector3(903,768,16);//posicion teorica (x,y,altura respecto al suelo)
        scale = new Vector3(300,300,0);
        angle = 2;//angulo en radianes
        background = new Texture("core\\assets\\fondo.png");
        backgroundPos = -256;
        entidades = new ArrayList<Sprite3D>();
        escalaEntidad = new Vector2(0.20f,0.20f);
        entidades.add(new Sprite3D(new Pixmap(new FileHandle("core\\assets\\carroAmarillo.png"))));


    }

    public void render(SpriteBatch batch){
        drawGround();
        pixmapTexture.draw(this,0,0);//se dibuja el pixmap en la textura
        batch.draw(pixmapTexture,0,0);//se dibuja la textura en pantalla
        batch.draw(background,backgroundPos,GameScreen.GAME_HEIGHT-40);//dibujar el horizonte
    }
    private void drawGround(){
        //direccion en x,y
        double dirx = Math.cos(angle);
        double diry = Math.sin(angle);

        //dibuja debajo del horizonte la pista
        for (int screeny = horizon; screeny < getHeight(); screeny++){
            //distancias para la perspectiva
            double distanceInWorldSpace = pos.z * scale.y/((double)screeny - horizon);
            double deltax = -diry * (distanceInWorldSpace/scale.x);
            double deltay = dirx * (distanceInWorldSpace/scale.y);

            //distancia real
            double spaceX = pos.x + dirx * distanceInWorldSpace - getWidth() / 2 * deltax;
            double spaceY = pos.y + diry * distanceInWorldSpace - getHeight() / 2 * deltay;

            //llena la pantalla con pasto para crear el suelo
            for(int screenx = 0 ; screenx < getWidth(); screenx++){
                setColor(grass.getPixel(((int)Math.abs(spaceX % grass.getWidth())),(int)Math.abs(spaceY%grass.getHeight())));
                drawPixel(screenx,screeny);

                setColor(track.getPixel((int) spaceX,(int) spaceY));
                drawPixel(screenx,screeny);

                spaceX += deltax;
                spaceY += deltay;
            }
        }
    }
}
