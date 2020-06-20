package server;

// Libraries
import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.EndScreen;
import entidades.Boost;
import entidades.Hole;
import entidades.Misil;
import entidades.Vida;
import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;
import json.parser.ParseException;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.MenuScreen;

public class Server extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader input;
    private MenuScreen menuScreen;
    private boolean otherPlayers = true;
    private int cantidadHuecos, cantidadVidas,cantidadTurbos;
    public Server(MenuScreen menuScreen){
        this.menuScreen = menuScreen;
    }

    /**
     * Start the connection to the Server
     *
     * @param ip   : ip address
     * @param port : server device port
     * @throws IOException : Something went wrong
     */
    public void startConnection(String ip, Integer port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * @param
     * @return
     */
    public void run() {
        String msg= "inicializacion";
        //Enviar mensaje al server Serializado
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("command",msg);
        msg = jsonSend.toJSONString();
        out.println(msg);

        //Recibir mensaje del Server y deserealizarlo
        String resp = null;
        try {
            resp = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        JSONObject jsonRec = null;
        try {
            jsonRec = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String respuesta = (String) jsonRec.get("command");
        System.out.println(respuesta);
        if (respuesta.equals("full")){
            System.out.println("Error, la sala está llena");
            Gdx.app.exit();
            return;
        }
        else if (respuesta.equals("identifiquese")){
            jsonSend.put("command","get_colors");
            msg = jsonSend.toJSONString();
            out.println(msg);
        }
        try {
            resp = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonRec = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        respuesta = (String) jsonRec.get("command");
        if (respuesta.equals("choose_a_color")){
            JSONArray arr = (JSONArray) jsonRec.get("available_colors");
            for (int i =0; i<arr.size();i++){
                int carro = Integer.parseInt(arr.get(i).toString());
                menuScreen.CarrosDisponibles.add(carro-10);
                System.out.println(menuScreen.CarrosDisponibles.get(i));
            }
        }
        System.out.println(respuesta);

        while (menuScreen.carroPrincipal == -1){

        }
        System.out.println("Ya envio");
        jsonSend.clear();
        jsonSend.put("color",menuScreen.carroPrincipal + 10);
        msg = jsonSend.toJSONString();
        out.println(msg);

        try {
            resp = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonRec = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        respuesta = (String) jsonRec.get("command");
        if (respuesta.equals("position_yourself")){
            while (true){ //Despues de este loop el usuario ya le dio a "Start"
                if(menuScreen.start){
                    updateLocation();
                    break;
                }
            }

        }
        while (true){
            getObjects();
            updateLocation();
        }


        //return;
    }

    public void stopConnection() throws IOException {
        input.close();
        out.close();
        clientSocket.close();
    }

    public void updateLocation(){
        String msg, respuesta;
        String resp = null;
        JSONObject jsonSend = new JSONObject();
        msg = jsonSend.toJSONString();
        JSONParser parser = new JSONParser();
        JSONObject jsonRec = null;


        //Enviar x,y
        jsonSend.clear();
        jsonSend.put("command","update_location");
        jsonSend.put("x",menuScreen.gameScreen.pixmap.carroPrincipal.sprite.position.x);
        jsonSend.put("y",menuScreen.gameScreen.pixmap.carroPrincipal.sprite.position.y);
        msg = jsonSend.toJSONString();
        out.println(msg);
        //System.out.println(msg);
        jsonSend.clear();

        //Recibir vidas, puntos y si el juego esta iniciado
        try {
            resp = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonRec = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        respuesta = (String) jsonRec.get("command");
        if (respuesta.equals("update")){
            Long vidas = (Long) jsonRec.get("vidas");
            Long puntos = (Long) jsonRec.get("puntos");
            Long velocidad = (Long) jsonRec.get("velocidad");
            menuScreen.gameScreen.pixmap.carroPrincipal.salud = vidas.intValue();
            menuScreen.gameScreen.pixmap.carroPrincipal.puntos = puntos.intValue();
            menuScreen.gameScreen.pixmap.carroPrincipal.velocidad = (0.0204*velocidad.intValue() + 0.3536);
            menuScreen.gameScreen.pixmap.carroPrincipal.velocidadReal = velocidad.intValue();
            menuScreen.gameScreen.gameStart = ((Long) jsonRec.get("start") != 0);
        }

        if (respuesta.equals("GameOver")) {
            JSONArray puntos = (JSONArray) jsonRec.get("puntos");
            JSONArray colores = (JSONArray) jsonRec.get("players");
            ArrayList<Vector2> puntosPorJugador = new ArrayList<>();

            for (int i =0; i<puntos.size();i++){
                int punto = Integer.parseInt(puntos.get(i).toString());
                int color = Integer.parseInt(colores.get(i).toString());
                Vector2 vector = new Vector2(punto,color);
                puntosPorJugador.add(vector);
            }
            EndScreen endScreen = new EndScreen(menuScreen.game,puntosPorJugador);
        }

        while(menuScreen.gameScreen.gameStart != true || otherPlayers){
            if(menuScreen.gameScreen.gameStart){
                jsonSend.clear();
                jsonSend.put("command","get_objects");
                msg = jsonSend.toJSONString();
                out.println(msg);
                try {
                    resp = input.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    jsonRec = (JSONObject) parser.parse(resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(jsonRec);
                JSONArray players = (JSONArray) jsonRec.get("players");
                System.out.println("jsonRec");
                for (int i=0; i<players.size();i++){
                    ArrayList <Long> pos = (ArrayList<Long>) players.get(i);
                    menuScreen.gameScreen.pixmap.agregarCarrosSecundarios(pos.get(2).intValue() -10 );
                }
                otherPlayers = false;
            }
            else{
                jsonSend.clear();
                jsonSend.put("command","update_location");
                jsonSend.put("x",menuScreen.gameScreen.pixmap.carroPrincipal.coordenadas.x);
                jsonSend.put("y",menuScreen.gameScreen.pixmap.carroPrincipal.coordenadas.y);
                msg = jsonSend.toJSONString();
                out.println(msg);
                jsonSend.clear();
                try {
                    resp = input.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    jsonRec = (JSONObject) parser.parse(resp);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                respuesta = (String) jsonRec.get("command");
                if (respuesta.equals("update")){
                    menuScreen.gameScreen.gameStart = ((Long) jsonRec.get("start") != 0);
                }
            }
        }

    }

    public void getObjects() {
        String msg, respuesta;
        String resp = null;
        JSONObject jsonSend = new JSONObject();
        msg = jsonSend.toJSONString();
        JSONParser parser = new JSONParser();
        JSONObject jsonRec = null;

        jsonSend.clear();
        jsonSend.put("command","get_objects");
        msg = jsonSend.toJSONString();
        out.println(msg);
        try {
            resp = input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            jsonRec = (JSONObject) parser.parse(resp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonRec);
        JSONArray players = (JSONArray) jsonRec.get("players");
        JSONArray vidas = (JSONArray) jsonRec.get("lives");
        JSONArray turbos = (JSONArray) jsonRec.get("turbos");
        JSONArray huecos = (JSONArray) jsonRec.get("holes");

        //Huecos
        CopyOnWriteArrayList<Vector2> listaVectores = new CopyOnWriteArrayList<>();
        for(int i =0; i<huecos.size(); i++){
            ArrayList <Long> pos = (ArrayList<Long>) huecos.get(i);
            Vector2 vector = new Vector2(pos.get(1).intValue()*30,pos.get(0).intValue()*30);
            listaVectores.add(vector);
            }
        if (cantidadHuecos != huecos.size()){
            menuScreen.gameScreen.pixmap.actualizarSprite(listaVectores,2);
            cantidadHuecos = huecos.size();
        }

        //Vidas
        listaVectores.clear();
        for(int i =0; i<vidas.size(); i++){
            ArrayList <Long> pos = (ArrayList<Long>) vidas.get(i);
            Vector2 vector = new Vector2(pos.get(1).intValue()*30,pos.get(0).intValue()*30);
            listaVectores.add(vector);
        }
        if (cantidadVidas != vidas.size()){
            menuScreen.gameScreen.pixmap.actualizarSprite(listaVectores,4);
            cantidadVidas = vidas.size();
        }

        //Turbos
        listaVectores.clear();
        for(int i =0; i<turbos.size(); i++){
            ArrayList <Long> pos = (ArrayList<Long>) turbos.get(i);
            Vector2 vector = new Vector2(pos.get(1).intValue()*30,pos.get(0).intValue()*30);
            listaVectores.add(vector);
        }
        if (cantidadTurbos != turbos.size()){
            menuScreen.gameScreen.pixmap.actualizarSprite(listaVectores,1);
            cantidadTurbos = turbos.size();
        }

        for(int i =0; i<players.size(); i++){
            ArrayList <Long> pos = (ArrayList<Long>) players.get(i);
            menuScreen.gameScreen.pixmap.objects.get(i+1).position.x=pos.get(0).intValue();
            menuScreen.gameScreen.pixmap.objects.get(i+1).position.y=pos.get(1).intValue() - 75;

        }
    }
}

