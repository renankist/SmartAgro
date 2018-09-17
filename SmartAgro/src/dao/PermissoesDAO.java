/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Colaborador;
import entidade.Modulo;
import entidade.Permissaoacesso;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Morgana
 */
public class PermissoesDAO {

    private static final Logger logger = Logger.getLogger(GenericDAO.class);

    public ArrayList<Permissaoacesso> consultarPermissoesUsuario(Colaborador usu) {

        ArrayList<Permissaoacesso> resultado = new ArrayList();

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Permissaoacesso where usuario = :user ");

            q.setInteger("user", usu.getId());

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

    public ArrayList<Permissaoacesso> consultarPermissoesUsuarioModulo(Colaborador usu, String modulo) {

        ArrayList<Permissaoacesso> resultado = new ArrayList();

        Session sessao = null;

        Modulo mod = consultaroModulo(modulo);

        try {
            if (mod == null) {
                throw new Exception("Módulo não cadastrado");
            }
        } catch (Exception e) {
            logger.error("Módulo não cadastrado", e);
            return resultado;
        }

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Permissaoacesso where usuario = :userParam");

            q.setInteger("userParam", usu.getId());

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registros", he);
            return resultado;
        } finally {
            sessao.close();
        }

        ArrayList<Permissaoacesso> permissoesModulo = new ArrayList();

        for (Permissaoacesso p : resultado) {
            if (p.getOperacoesmodulo().getModulo().getId() == mod.getId()) {
                permissoesModulo.add(p);
            }
        }

        return permissoesModulo;

    }

    public Modulo consultaroModulo(String modulo) {

        Modulo resultado = new Modulo();

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Modulo where nome = :nomeParam ");

            q.setString("nomeParam", modulo);

            resultado = (Modulo) q.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

}
