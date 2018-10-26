/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Cidade;
import entidade.Colaborador;
import entidade.Estado;
import entidade.Produto;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

/**
 *
 * @author renan
 */
public class CidadeDAO extends GenericDAO<Colaborador> {

    private static final Logger logger = Logger.getLogger(CidadeDAO.class);

    public Cidade consultarPorCidadeUF(String cidade, String uf) {

        Cidade c = new Cidade();

        Session sessao = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            sessao.beginTransaction();

            Criteria crit = sessao.createCriteria(Cidade.class);
            
            crit.add(Restrictions.eq("nome",cidade));
            //crit.add(Restrictions.eq("sigla", uf));
            
            crit.createAlias("estado", "e", Criteria.INNER_JOIN, Restrictions.like("e.sigla",uf ));
            
            
            c = (Cidade) crit.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return c;

    }

}
