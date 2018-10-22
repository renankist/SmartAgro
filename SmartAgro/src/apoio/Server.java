/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTextArea;

/**
 *
 * @author renan
 */
public class Server implements Runnable {

    private ServerSocket server;
    private boolean starter;
    private boolean running;
    private Thread t;
    private List<Atendente> atendentes;

    public Server(int port) throws IOException {
        atendentes = new ArrayList<Atendente>();
        starter = false;
        running = false;
        open(port);

    }

    private void open(int p) throws IOException {
        server = new ServerSocket(p);
        starter = true;

    }

    private void close() {
        for (Atendente atendente : atendentes) {
            try {
                atendente.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        server = null;
        starter = false;
        running = false;
        t = null;
    }

    public void start() {
        if (!starter || running) {
            return;
        }
        running = true;
        t = new Thread(this);
        t.start(); // Chma método  "run"

    }

    public void stop() throws InterruptedException {
        running = false;
        if (t != null) {
            t.join(); // bloqueia thread atual até que o RUN retorne
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        System.out.println("Server is loading...");
        Server s = new Server(5000);
        s.start();

        System.out.println("Press enter to finalize the server");
        new Scanner(System.in).nextLine();

        System.out.println("Server is closing");
        s.stop();
    }

    public void sendToAll(Atendente origem, String mensagem) throws Exception {
        Iterator<Atendente> i = atendentes.iterator();

        while (i.hasNext()) {
            Atendente atendente = i.next();
            if (origem != atendente) {
                try {
                    atendente.send(mensagem);
                } catch (Exception e) {
                    atendente.stop();
                    i.remove();

                }
            }

        }
    }

    @Override
    public void run() {
        System.out.println("Waiting for connection");
        while (running) {
            try {
                server.setSoTimeout(2500);
                Socket socket = server.accept();
                System.out.println("Connection was estabelish");
                Atendente a = new Atendente(this, socket);
                a.start();
                atendentes.add(a);

            } catch (SocketTimeoutException e) {
                //
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        close();
    }

}
