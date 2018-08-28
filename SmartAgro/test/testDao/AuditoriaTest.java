/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDao;

import apoio.HibernateUtil;
import auditoria.FormapagamentoAud;
import dao.AuditoriaDAO;
import dao.GenericDAO;
import entidade.Formapagamento;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;

import org.hibernate.envers.query.AuditQuery;

/**
 *
 * @author rlkist
 */
public class AuditoriaTest {

    public static void main(String args[]) {
        //testVertical();
        verTodas();
        //consultid();
    }
   
    
    public static void verTodas(){
        
        AuditoriaDAO<FormapagamentoAud> dao = new AuditoriaDAO(); 
        
        ArrayList<FormapagamentoAud> aud = new ArrayList(); 
      
        
        aud = dao.consultarPorIdVarios("FormapagamentoAud", 5);
        
        for(FormapagamentoAud a : aud){
            
            System.out.println(aud.get(0).getCustomRevInfo().getUsername());
            
            
        }
        
        
        
    }
    
    public static void consultid(){
        
        
        
          AuditoriaDAO<FormapagamentoAud> dao = new AuditoriaDAO(); 
          
          
          
          System.out.println(dao.consultarPorId(5, "FormapagamentoAud").getId()); 
          
          
        
    }

    public static void testVertical() {
        
        
        
        Session sessao = null;

        sessao = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = sessao.beginTransaction();
        
        AuditReader ar  = AuditReaderFactory.get(sessao);
        
        AuditQuery q = ar.createQuery().forRevisionsOfEntity(Formapagamento.class, true, true);
        
        q.add(AuditEntity.id().eq(new Formapagamento(6).getId()));

        List<Formapagamento> audit = q.getResultList();
        
        
        
        for(Formapagamento fp : audit){
            System.out.println(fp.getDescricao());
        }
        
        
        sessao.getTransaction().commit();
        sessao.close();
        
    }

    public void testHorizontal(){
        
        Session sessao = null;

        sessao = HibernateUtil.getSessionFactory().openSession();
        
        Transaction t = sessao.beginTransaction();
        
         AuditReader ar  = AuditReaderFactory.get(sessao);
        
         AuditQuery q = ar.createQuery().forEntitiesAtRevision(Formapagamento.class, 2);
         
         
        
        
        
        
    }

    

}
