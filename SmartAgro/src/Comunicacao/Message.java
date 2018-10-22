/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacao;

import entidade.Colaborador;
import entidade.Produto;
import java.io.Serializable;

/**
 *
 * @author renan
 */
public class Message implements Serializable {
    
    private Status status; 
    private String msg; 
    private Produto p; 
    private String toWho; 
    private Colaborador c; 

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Produto getP() {
        return p;
    }

    public void setP(Produto p) {
        this.p = p;
    }

    public String getToWho() {
        return toWho;
    }

    public void setToWho(String toWho) {
        this.toWho = toWho;
    }

    public Colaborador getC() {
        return c;
    }

    public void setC(Colaborador c) {
        this.c = c;
    }
    
    
    
    
    
    
    
    
}
