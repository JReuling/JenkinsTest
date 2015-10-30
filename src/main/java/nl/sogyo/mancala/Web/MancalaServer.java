package nl.sogyo.mancala.Web;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.spi.HttpServerProvider;

/**
 * Created by jreuling on 22-10-2015.
 */
import java.io.*;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import nl.sogyo.mancala.Game;


public class MancalaServer {


    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/mancala", new mancalaHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class mancalaHandler implements HttpHandler {
        WebGameControl webGameControl;

        public void handle(HttpExchange t) throws IOException {
            System.out.println("HTTP Request received.");
            String response = "";
            if (webGameControl == null) {
                Game game = new Game();
                game.firstStartOnWeb();
                webGameControl = (WebGameControl) game.getControl();
                response = webGameControl.getUIasString();
            }
            else if (correctSyntaxPitPlay(t))
            {
                playPit(t);
                response = newBoardState();
            }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String newBoardState() {
            StringBuilder responseBuilder = new StringBuilder("");
            for (int i = 1; i < 14; i++) {
                if (i == 7) {continue;}
                responseBuilder.append("Pit-"+ i + "=" + webGameControl.getStones(i) + ";" );
            }
            return responseBuilder.toString();
        }

        private void playPit(HttpExchange t) {
            InputStream input = t.getRequestBody();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            int playedPitNumber = 0;
            try {
                playedPitNumber = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(playedPitNumber);
            webGameControl.play(playedPitNumber);
        }

        private boolean correctSyntaxPitPlay(HttpExchange t) {
            System.out.println("in correct syntax check");
            return (t.getRequestMethod().equalsIgnoreCase("POST")) || t.getRequestHeaders().containsKey("PitToPlay");
        }
    }
}

