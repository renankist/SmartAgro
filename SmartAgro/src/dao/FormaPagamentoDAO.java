/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Formapagamento;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author rlkist
 */
public class FormaPagamentoDAO extends GenericDAO {
    
     public ArrayList<Formapagamento> consultarTodasFormas() {

        ArrayList resultado = null;
        
        Session sessao = null;
        try {

             sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Formapagamento");
            
            
            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        }finally{
            sessao.close();
        }

        return resultado;

    }
    
    
    
}
