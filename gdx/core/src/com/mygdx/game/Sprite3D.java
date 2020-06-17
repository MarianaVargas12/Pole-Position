package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Sprite3D implements Comparable<Sprite3D> {
    public Pixmap pixmap;
    public int sort;
    public Vector2 screen;//posicion en la pantalla
    public Vector2 size;//tamano en el juego
    public Vector2 position;//posicion en el juego

    public Sprite3D(Pixmap pixmap, int carro){
        this.pixmap = pixmap;
        screen = new Vector2();
        size = new Vector2();

        if (carro == 1){
            position = new Vector2(520,1075);//posicion inicial de los carro
        }

        else if (carro == 2 ){
            position = new Vector2(600,1075);//posicion inicial de los carro
        }

        else if (carro == 3){
            position = new Vector2(520,1155);//posicion inicial de los carro
        }

        else  {
            position = new Vector2(600,1155);//posicion inicial de los carro
        }

    }
    //coloca sprite de los huecos,boosts y bombas
    public Sprite3D(Pixmap pixmap,int x,int y){

        this.pixmap = pixmap;
        screen = new Vector2();
        size = new Vector2();
        position = new Vector2(x,y);
    }

    @Override
    public int compareTo(Sprite3D other){
        return Integer.compare(sort,other.sort);
    }
}
