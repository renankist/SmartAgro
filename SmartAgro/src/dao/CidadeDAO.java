/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Cidade;
import entidade.Colaborador;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author renan
 */
public class CidadeDAO extends GenericDAO<Colaborador> {
    
    public Cidade consultarPorCidadeUF(String cidade, String uf){
        
        
        
        Cidade  c = new Cidade();
       
        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Cidade as c, Estado as e WHERE c.estado = e.id and c.nome like :cidParam and e.uf like :estado");

            q.setString("cidParam",cidade);
            q.setString("estado", uf);

             c = (Cidade) q.uniqueResult();
            

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return c;
       
        
    }
    
    
    
    
}
