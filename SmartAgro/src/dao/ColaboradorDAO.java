/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Colaborador;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Renan Luis Kist
 */
public class ColaboradorDAO extends GenericDAO<Colaborador> {
    
    
   public Colaborador autenticarColaborador(String usuario, String senha) {
        
       Colaborador c  = new Colaborador();
       
        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Colaborador where usuario = :usuarioParam and senhausuario = :senhaParam");

            q.setString("usuarioParam", usuario);
            q.setString("senhaParam", senha);

            c = (Colaborador) q.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return c;
            
   }
    
    
}
