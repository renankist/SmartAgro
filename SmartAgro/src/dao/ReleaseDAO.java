/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Release;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Morgana
 */
public class ReleaseDAO extends GenericDAO {

    private static final Logger logger = Logger.getLogger(ReleaseDAO.class);

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
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return c;

    }

    public ArrayList<Release> consultarReleaseParaVisualizarUsuario(int usuario, boolean soNaoVistos) {

        ArrayList resultado = null;

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createSQLQuery("select r.* from Release r, Visualizacaorelease v where r.id = v.release and v.usuario = :usuarioParam and v.visto = :vistoParam").addEntity(Release.class);

            q.setInteger("usuarioParam", usuario);
            q.setBoolean("vistoParam", !soNaoVistos);
            
            resultado = (ArrayList) q.list();
            
        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

}
