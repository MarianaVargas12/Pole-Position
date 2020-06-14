package entidades;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Sprite3D;

public class Carro {
    public int salud;
    public int puntos;
    public int velocidad;
    public Sprite3D sprite;
    public Vector3 camara;
    public Vector2 coordenadasCarro;

    public Carro(int numeroCarro){
        this.salud = 3;
        this.puntos = 0;
        this.velocidad = 3;

        if(numeroCarro == 1){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\carroAmarillo.png")),numeroCarro);
            this.camara = new Vector3(520,1150,25);
        }
        else if(numeroCarro == 2){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\carroVerde.png")),numeroCarro);
            this.camara = new Vector3(600,1150,25);
        }
        else if(numeroCarro == 3){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\carroNegro.png")),numeroCarro);
            this.camara = new Vector3(520,1230,25);
        }
        else {
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\carroBlanco.png")),numeroCarro);
            this.camara = new Vector3(600,1230,25);
        }

        this.coordenadasCarro = new Vector2(sprite.position.x,sprite.position.y);
    }
}
