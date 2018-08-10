/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDao;

import dao.GenericDAO;
import entidade.Formapagamento;

/**
 *
 * @author rlkist
 */
public class TestFormaPagamentoDao {
        
    public static void main(String args[]){
        salvar();
    }
    public static void salvar(){
        Formapagamento f = new Formapagamento(); 
  
        f.setDescricao("Teste");
        f.setAtivo(true);
        
        GenericDAO dao = new GenericDAO(); 
        
        if(dao.salvar(f)){
           System.out.println("Sucesso");
        }else{
            System.out.println("Erro");
        }
    }
    
}
