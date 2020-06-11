package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Sprite3D implements Comparable<Sprite3D> {
    public Pixmap pixmap;
    public int sort;
    public Vector2 screen;
    public Vector2 size;
    public Vector2 position;

    public Sprite3D(Pixmap pixmap){
        this.pixmap = pixmap;
        screen = new Vector2();
        size = new Vector2();
        position = new Vector2(0,0);
    }
    @Override
    public int compareTo(Sprite3D other){
        return Integer.compare(sort,other.sort);
    }
}
