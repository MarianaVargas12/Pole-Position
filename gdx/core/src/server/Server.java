package server;

// Libraries
import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;

import json.JSONArray;
import json.JSONObject;
import json.parser.JSONParser;
import json.parser.ParseException;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.MenuScreen;

public class Server{

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader input;
    private MenuScreen menuScreen;
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
     * @param msg
     * @return
     * @throws IOException
     */
    public String sendMessage(String msg) throws IOException, ParseException {
        //Enviar mensaje al server Serializado
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("command",msg);
        msg = jsonSend.toJSONString();
        out.println(msg);

        //Recibir mensaje del Server y deserealizarlo
        String resp = input.readLine();
        JSONParser parser = new JSONParser();
        JSONObject jsonRec = (JSONObject) parser.parse(resp);
        String respuesta = (String) jsonRec.get("command");
        System.out.println(respuesta);
        if (respuesta.equals("full")){
            System.out.println("Entra");
            Gdx.app.exit();
            return "0";
        }
        else if (respuesta.equals("identifiquese")){
            jsonSend.put("command","get_colors");
            msg = jsonSend.toJSONString();
            out.println(msg);
        }
        resp = input.readLine();
        jsonRec = (JSONObject) parser.parse(resp);
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

        jsonSend.put("color",menuScreen.carroPrincipal);
        msg = jsonSend.toJSONString();
        out.println(msg);

        return "jeje";
    }

    public void stopConnection() throws IOException {
        input.close();
        out.close();
        clientSocket.close();
    }
}

