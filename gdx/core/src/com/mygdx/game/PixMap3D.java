package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.text.Normalizer;

public class PixMap3D extends Pixmap {
    private Texture pixmapTexture;
    private int horizon;
    public double angle;
    private Pixmap grass; //informacion del pasto en un arreglo 2D
    public Vector3 pos;
    public Vector3 scale;

    public PixMap3D (int width, int height, Format format){
        super(width,height,format);
        setFilter(Filter.NearestNeighbour);//modo en el que coloca los pixeles

        pixmapTexture = new Texture(this,getFormat(),true);
        horizon = 30;//distancia en pixeles a partir de la parte superior de la pantalla
        grass = new Pixmap(new FileHandle("C:\\Users\\Mario\\Desktop\\gdx\\core\\assets\\pasto.png"));
        pos = new Vector3(903,768,16);//posicion teorica (x,y,altura respecto al suelo)
        scale = new Vector3(300,300,0);
        angle = 2;//angulo en radianes
    }

    public void render(SpriteBatch batch){
        drawGround();
        pixmapTexture.draw(this,0,0);//se dibuja el pixmap en la textura
        batch.draw(pixmapTexture,0,0);//se dibuja la textura en pantalla
    }
    private void drawGround(){
        //direccion en x,y
        double dirx = Math.cos(angle);
        double diry = Math.sin(angle);

        //dibuja debajo del horizonte la pista
        for (int screeny = horizon;screeny < getHeight(); screeny++){
            //distancias para la perspectiva
            double distanceInWorldSpace = pos.z * scale.y/((double)screeny - horizon);
            double deltax = -diry * (distanceInWorldSpace/scale.x);
            double deltay = dirx * (distanceInWorldSpace/scale.x);

            //distancia real
            double spaceX = pos.x + dirx + distanceInWorldSpace - getWidth() / 2 * deltax;
            double spaceY = pos.y + diry + distanceInWorldSpace - getHeight() / 2 * deltay;

            //llena la pantalla con pasto para crear el suelo
            for(int screenx = 0 ; screenx < getWidth(); screenx++){
                setColor(grass.getPixel(((int)Math.abs(spaceX % grass.getWidth())),(int)Math.abs(spaceY%grass.getHeight())));
                drawPixel(screenx,screeny);
                spaceX += deltax;
                spaceY += deltay;
            }
        }
    }
}
