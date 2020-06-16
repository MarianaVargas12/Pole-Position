
import org.json.simple.parser.ParseException;
import server.Server;

import java.io.IOException;


class Main{

    public static void main(String[] args) throws IOException, ParseException {
        Server server = new Server();
        server.startConnection("127.0.0.1",8888);
        String a = server.sendMessage("Ya hay sockeeeeeets");
        System.out.println("Server: "+a);
        server.stopConnection();
    }


}