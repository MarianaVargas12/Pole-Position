package server;

// Libraries
import java.io.*;
import java.net.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Server {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader input;

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
        return respuesta;
    }

    public void stopConnection() throws IOException {
        input.close();
        out.close();
        clientSocket.close();
    }
}

