package Comunicacao;

import java.io.IOException;
import java.net.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class ServerThread extends Thread {

    Socket socket = null;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); //Quando precisa enviar algo para o socket
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream()); //Quando precisa pegar uma mensagem do socket 

            Message m = (Message) input.readObject();

            m.setStatus(Status.OK);
            m.setMsg("Produto: " + m.getP().getDescricao() + " com estoque abaixo do limite. Mensagem enviado por: " + m.getC().getNomecompleto());
            m.setC(m.getC());
            m.setP(m.getP());

            output.writeObject(m);
            output.flush();

            input.close();
            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
