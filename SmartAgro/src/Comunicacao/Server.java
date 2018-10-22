package Comunicacao;

import java.net.*;
import java.io.IOException;

public class Server {

    public static final int PORT = 5000;
 
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        new Server().runServer();
    }

    public void runServer() throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server is ready for new connections");
        while (true) {
            Socket socket = serverSocket.accept();
            new ServerThread(socket).start();
        }
    }
}
