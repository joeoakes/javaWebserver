import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static void main(String[] args) throws IOException {
        // Create an HTTP server listening on port 8080
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Create a context for handling requests and set the handler
        server.createContext("/", new MyHandler());

        // Start the server
        server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started on port " + port);
    }

    // Define a custom request handler
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Get the output stream to send the response
            OutputStream os = exchange.getResponseBody();

            // Create a simple HTML response
            String response = "<html><body><h1>Hello, World!</h1></body></html>";

            // Set response headers and send the response
            exchange.sendResponseHeaders(200, response.length());
            os.write(response.getBytes());
            os.close();
        }
    }
}
