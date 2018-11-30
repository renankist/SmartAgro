/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Colaborador;
import entidade.Release;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Renan Luis Kist
 */
public class ColaboradorDAO extends GenericDAO<Colaborador> {
    
    private static final Logger logger = Logger.getLogger(ColaboradorDAO.class);

    public Colaborador autenticarColaborador(String usuario, String senha) {

        Colaborador c = new Colaborador();

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

    public boolean consultarSenha(String user, String senha) {

        boolean c = false;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Colaborador where usuario = :usuarioParam and senhausuario = :senhaParam");

            q.setString("usuarioParam", user);
            q.setString("senhaParam", senha);

            if (q.uniqueResult() != null) {
                c = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return c;

    }
    
    public boolean existeReleaseParaVisualizar(int user) {

        boolean c = false;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createSQLQuery("select COUNT(*) from Visualizacaorelease where usuario = :usuarioParam and visto = false");

            q.setInteger("usuarioParam", user);

            if (q.uniqueResult() != null) {
                c = true;
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            sessao.close();
        }

        return c;

    }
    
    public ArrayList<Object> consultarReleaseParaVisualizarUsuario(int usuario, boolean soNaoVistos) {

        ArrayList resultado = null;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();
            
            org.hibernate.Query q = sessao.createSQLQuery("select COUNT(*) from Visualizacaorelease where usuario = :usuarioParam and visto = false");

            q.setInteger("usuarioParam", user);

            Criteria crit = sessao.createCriteria(Release.class);
            
            crit.add(Restrictions.eq("usuario", usuario));
            
            if (soNaoVistos) {
                crit.createAlias("visualizacaorelease", "v");
                crit.add(Restrictions.eq("v.visto", "false"));
            }
            
            resultado = (ArrayList) crit.list();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

}
