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
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
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
            
            // O problema ta aqui, o hibernate nao ta conseguindo converter o ID do estado em um objeto Estado

            org.hibernate.Query q = sessao.createSQLQuery("select c.* from Cidade as c, Estado as e WHERE c.estado = e.id and upper(c.nome) like upper(:cidParam) and upper(e.sigla) like upper(:estado)")
                    .addSynchronizedEntityClass(Estado.class);

            q.setString("cidParam", cidade);
            q.setString("estado", uf);
            
            q.setResultTransformer(Transformers.aliasToBean(Cidade.class));//Sem isso aqui impossível de retornar
            
            c = (Cidade) q.uniqueResult();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registro", he);
        } finally {
            sessao.close();
        }

        return c;

    }

}
