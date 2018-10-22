package apoio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import javax.swing.JTextArea;

public class Client implements Runnable {

    private Socket socket;
    private BufferedReader in;
    private PrintStream out;
    private boolean started;
    private boolean running;
    private Thread t;
    private JTextArea area; 
    
    
    public Client(String endereco, int port, JTextArea area) throws Exception {
        started = false;
        running = false;
        open(endereco, port);
        this.area = area; 

    }

    private void open(String endereco, int port) throws IOException {
        try {
            socket = new Socket(endereco, port);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());

            started = true;
        } catch (Exception e) {
            e.printStackTrace();
            close();
            throw e;
        }
    }

    public void close() {
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

        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        in = null;
        out = null;
        socket = null;
        started = false;
        running = false;
        t = null;
    }

    public void start() {

        if (!started || running) {
            return;
        }

        running = true;
        t = new Thread(this);
        t.start();

    }

    public void stop() throws Exception {
        running = false;
        if (t != null) {
            t.join();
        }
    }

    public void send(String m) {
        out.println(m);
    }

    public boolean isRunning() {
        return this.running;
    }

    

    @Override
    public void run() {
        while (running) {
            try {
                socket.setSoTimeout(2500);

                String mensagem = in.readLine();

                if (mensagem == null) {
                    break;
                }
                area.append(mensagem);
                area.append("\n");
                System.out.println("Mensagem enviada pelo servidor " + mensagem);
            } catch (SocketTimeoutException e) {

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }

        close();
    }

}
