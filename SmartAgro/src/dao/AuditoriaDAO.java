/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import telas.jfrLogin;

/**
 *
 * @author renan
 */
public class AuditoriaDAO extends GenericDAO {

    private static final Logger logger = Logger.getLogger(GenericDAO.class);

    public boolean arquivarAuditoria(final Date inicio, final Date fim) {

        Boolean r = false;

        Session sessao = null;

        sessao = HibernateUtil.getSessionFactory().openSession();

        Transaction t = sessao.beginTransaction();

        try {

            sessao.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    CallableStatement call = connection.prepareCall("{ call fn_arq_audit(?,?) }");
                    call.setTimestamp(1, new java.sql.Timestamp(inicio.getTime()));
                    call.setTimestamp(2, new java.sql.Timestamp(fim.getTime()));
                    call.execute();
                }
            });

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
