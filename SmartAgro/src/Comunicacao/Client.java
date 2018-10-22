/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacao;

import entidade.Produto;
import entidade.Colaborador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.IOException;
import telas.FrmPrincipal;
import telas.jfrLogin;

/**
 *
 * @author renan
 */
public class Client {

    public static void main(String args[]) {
        /*
            1  - Estabelecer conexão com o server;
            2  - Trocar mensagens com o servidor; 
         */
        try {
            Produto p = new Produto();
            p.setDescricao("Mesa");
            Colaborador c = new Colaborador();
            c.setNomecompleto("Renan");
            Socket s = new Socket("localhost", 5000);
            ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(s.getInputStream());

            Message m = new Message();
            m.setP(p);
            m.setC(c);
            m.setStatus(Status.WAITING);
            m.setToWho("all");
            
            output.writeObject(m);
            output.flush();

            m = (Message) input.readObject();
            
            System.out.println(m.getMsg());
 
            input.close();
            output.close();
            s.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
