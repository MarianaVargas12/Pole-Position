package entidades;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Sprite3D;

public class Boost {
    public Vector2 coordenadas;
    public Sprite3D sprite;

    public Boost(int x, int y){
        this.coordenadas = new Vector2(x,y);
        this.sprite = new Sprite3D(new Pixmap(new FileHandle("core\\assets\\boost.png")),x,y);
    }
}

