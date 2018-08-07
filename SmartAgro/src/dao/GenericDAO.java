package dao;

import apoio.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDAO {
    
    public boolean salvar(Object o) {

        Boolean r = false;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            sessao.save(o);

            t.commit();

            r = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {

            sessao.close();
        }

        return r;

    }

    public ArrayList<Object> consultarTodos(String className) {

        ArrayList resultado = null;

        try {

            Session sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from " + className);

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return resultado;

    }

    public boolean atualizar(Object o) {

        Boolean r = false;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            Transaction t = sessao.beginTransaction();

            sessao.update(o);

            t.commit();

            r = true;

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }
        return r;
    }
    
    public Object consultarPorId(int id){
        Object o = null; 
        
        
        
        
        
        
        return o; 
    }

}
