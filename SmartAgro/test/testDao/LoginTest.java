/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDao;

import dao.ColaboradorDAO;
import entidade.Colaborador;

/**
 *
 * @author Renan Luis Kist
 */
public class LoginTest {
    public static void main(String args[]){
        
        ColaboradorDAO dao = new ColaboradorDAO();
        
        if(dao.autenticarColaborador("renankist", "12345") != null){
            System.out.println("Autenticado com sucesso.");
        
        }else{
            System.out.println("Erro ao autenticar");
        }
    }
        
        
    
}
