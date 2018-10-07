/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author renan
 */
public class AuditoriaDAO extends GenericDAO{
    
    private static final Logger logger = Logger.getLogger(GenericDAO.class);
    
    
    public boolean arquivarAuditoria(Date inicio, Date fim){
        
        Boolean r = false;

        Session sessao = null;

        sessao = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = sessao.beginTransaction();

        try {

            sessao.createSQLQuery("delete from custom_rev_info_arq");
            
            
            
            
            t.commit();

            r = true;

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao salvar registro", he);
        } finally {
            if (r == false) {
                t.rollback();
            }

            sessao.close();
        }

        return r;
    }
    
}
