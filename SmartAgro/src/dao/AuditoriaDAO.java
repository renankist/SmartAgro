/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import apoio.Validacao;
import auditoria.CustomRevInfo;
import auditoria.FormapagamentoAud;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Renan Luis Kist
 */
public class AuditoriaDAO<Object> extends GenericDAO<Object> {

    public ArrayList<Object> consultarPorIdVarios(String className, int id) {
        ArrayList resultado = new ArrayList();

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from " + className + " where id = :idParam");

            q.setInteger("idParam", id);

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {

            sessao.close();
        }

        return resultado;
    }

    public ArrayList<Object> consultarPorData(Date ini, Date fim, Class className, String acao, int reg) {

        ArrayList resultado = null;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            Criteria cr = sessao.createCriteria(className);

            Criteria crCrit = cr.createCriteria("customRevInfo");

            crCrit.add(Restrictions.ge("hora", ini));
            crCrit.add(Restrictions.le("hora", fim));
            crCrit.addOrder(Order.desc("hora"));
            
            if(reg != 0){
                 cr.add(Restrictions.eq("id", reg));
            }
            
           
            
           
            
            if(acao.equals("Inserção")){
                cr.add(Restrictions.eq("revtype", new Short("0")));
            }else if(acao.equals("Alteração")){
                cr.add(Restrictions.eq("revtype", new Short("1")));
            }else if(acao.equals("Remoção")){
                cr.add(Restrictions.eq("revtype", new Short("2")));
            }
            
            
            resultado = (ArrayList) cr.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return resultado;

    }

}
