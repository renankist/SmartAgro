/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidade.Cidade;

/**
 *
 * @author renan
 */
public class teste {
    public static void main(String args[]){
        Cidade c = new Cidade();
        c = new CidadeDAO().consultarPorCidadeUF("Lajeado", "RS");
        System.out.println(c.getNome());
    }
}
