package apoio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

public class Atendente implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private boolean starter;
    private boolean running;
    private Thread t;
    private Server server; 
    
    
    public Atendente(Server server,Socket s) throws IOException, Exception {
        this.socket = s;
        this.server = server;
        this.starter = false;
        this.running = false;
        open(); 
    }

    @Override
    public void run() {

        while (running) {
            try {

                socket.setSoTimeout(2500 );

                String mensagem = in.readLine();

                if(mensagem == null ){
                    break;
                }
                
                System.out.println("Mensagem recebida do cliente [" + socket.getInetAddress().getHostAddress() + " - " + socket.getPort() + "] : " + mensagem);

                if ("FIM".equals(mensagem)) {
                    break;
                }

              //out.println(mensagem);
              server.sendToAll(this, mensagem);
              
            } catch (SocketTimeoutException e) {
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

           
        }
         System.out.println("Closing the connection");

            close();
    }
    
    public void send(String m){
        out.println(m);
    }
    
    private void open() throws Exception {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            starter = true;
        } catch (Exception e) {
            close();
            throw e;
                
        }

    }

    private void close() {
        if (in != null) {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        in = null;
        out = null;
        socket = null;
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
        t.start();
    }

    public void stop() throws InterruptedException {
        running = false;
        if (t != null) {
            t.join();
        }
    }
}
