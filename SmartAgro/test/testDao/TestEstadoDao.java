/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDao;

import apoio.HibernateUtil;
import dao.GenericDAO;
import entidade.Estado;
import static java.lang.System.exit;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Renan Luis Kist
 */
public class TestEstadoDao {

    public static void main(String args[]) {

        //salvar();
        //consultarTodos(); 
        //atualizar();
        //consultarById(); 
        consultarPorCriterio();
        exit(0);
    }

    public static void salvar() {

        Estado e = new Estado();

        e.setNome("Rio Grande de Palmas");
        e.setSigla("RP");

        GenericDAO dao = new GenericDAO();

        if (dao.salvar(e)) {
            System.out.println("Salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar!");
        }

    }

    public static void consultarTodos() {

        GenericDAO dao = new GenericDAO<Estado>();

        ArrayList<Estado> es = dao.consultarTodos("Estado");

        for (int i = 0; i < es.size(); i++) {

            
            System.out.println(es.get(i).getNome());

        }

    }

    public static void atualizar() {

        Estado e = new Estado();

        e.setId(1);
        e.setNome("Vamo");
        e.setSigla("VM");

        GenericDAO dao = new GenericDAO();

        if (dao.atualizar(e)) {
            System.out.println("Atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizado!");
        }

    }
    
    public static void consultarById(){
        
        GenericDAO dao = new GenericDAO();
        
        Estado e = (Estado) dao.consultarPorId(4, "Estado");
        
        System.out.println(e.getId()+" - "+e.getNome());
        
    }
    
    public static void consultarPorCriterio(){
        
        GenericDAO<Estado> dao = new GenericDAO();

        ArrayList<Estado> es = dao.consultarComCriterio("Estado", "nome", "Rio Grande do Sul", false);

        for (int i = 0; i < es.size(); i++) {

            
            System.out.println(es.get(i).getNome());

        }
        
    }

}
