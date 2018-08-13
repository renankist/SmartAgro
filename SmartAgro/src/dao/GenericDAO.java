package dao;

import apoio.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class GenericDAO<Object> {

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

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from " + className);

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
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

    public Object consultarPorId(int id, String className) {
        Object o = null;
        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from " + className + " where id = :idParam");

            q.setInteger("idParam", id);

            o = (Object) q.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return o;
    }

    public ArrayList<Object> consultarComCriterio(String className, String criterio, String valor, boolean soAtivos) {

        return realizaConsulta(false, className, criterio, valor, soAtivos);
    }

    public ArrayList<Object> consultarComCriterioIgualA(String className, String criterio, String valor, boolean soAtivos) {

        return realizaConsulta(true, className, criterio, valor, soAtivos);
    }

    private ArrayList<Object> realizaConsulta(boolean valorExato, String className, String criterio, String valor, boolean soAtivos) {
        ArrayList resultado = null;

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String sql = "from " + className + " where upper(" + criterio + ") like upper(:criterio)";

            if (soAtivos) {
                sql = sql + " and ativo = :ativ";
            }

            org.hibernate.Query q = sessao.createQuery(sql);

            if (valorExato) {
                q.setString("criterio", valor);
            } else {
                q.setString("criterio", "%" + valor + "%");
            }

            if (soAtivos) {
                q.setBoolean("ativ", true);
            }

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return resultado;
    }

}
