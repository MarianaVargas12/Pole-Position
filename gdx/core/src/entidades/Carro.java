package entidades;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Sprite3D;

import java.util.ArrayList;

public class Carro extends entidad {
    public int salud;
    public int puntos;
    public double velocidad;
    public int velocidadReal;
    public Vector3 camara;
    public ArrayList<Misil> bombas;
    public int id;
    public boolean bomb = false;

    public Carro(int numeroCarro){
        this.salud = 3;
        this.puntos = 0;
        this.velocidad = 3;
        this.bombas = new ArrayList<>();
        
        if(numeroCarro == 0){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\Negro.png")),numeroCarro);
            this.camara = new Vector3(520,1150,25);

        }
        else if(numeroCarro == 1){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\Verde.png")),numeroCarro);
            this.camara = new Vector3(600,1150,25);
        }
        else if(numeroCarro == 2){
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\Blanco.png")),numeroCarro);
            this.camara = new Vector3(520,1230,25);
        }

        else {
            this.sprite = new Sprite3D(new Pixmap(new FileHandle(
                    "core\\assets\\Amarillo.png")),numeroCarro);
            this.camara = new Vector3(600,1230,25);
        }
        this.id=numeroCarro;
        this.sprite.tipo = 0;
        this.coordenadas = new Vector2(sprite.position.x,sprite.position.y);
    }
}
