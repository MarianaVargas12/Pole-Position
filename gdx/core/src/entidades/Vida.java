package entidades;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Sprite3D;

public class Vida extends entidad{
    public Vida(int x,int y){
        this.coordenadas = new Vector2(x,y);
        this.sprite = new Sprite3D(new Pixmap(new FileHandle("core\\assets\\corazon.png")),x,y);
    }
}
