/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Cidade;
import entidade.Venda;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author renan
 */
public class GraficoDAO extends GenericDAO<Object> {

    public ArrayList<Venda> vendasFinalizadasMesAtual() {

        ArrayList<Venda> resultado = new ArrayList();

        Calendar cal = Calendar.getInstance();

        int mes = cal.get(Calendar.MONTH);
        mes += 1;

        String ultimo = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String primeiro = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Venda where dia between '" + primeiro + "' and '" + ultimo + "' and status = 'F'");

            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

    public ArrayList<String> vendaPorColaborador() {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT c.nomecompleto as vendedor, count(v.id) as quantVenda "
                    + " FROM Venda as v, Colaborador as c "
                    + " where c.id = v.vendedor "
                    + "group by c.nomecompleto";

            List<?> lista = sessao.createQuery(hql).list();

            for (int i = 0; i < lista.size(); i++) {
                Object[] row = (Object[]) lista.get(i);
                resultado.add(row[0] + "," + row[1]);
            }
            
        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }
    
     public ArrayList<String> valorVendidoPorColaborador() {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT c.nomecompleto as vendedor, sum(v.valortotal) as quantVenda "
                    + " FROM Venda as v, Colaborador as c "
                    + " where c.id = v.vendedor "
                    + "group by c.nomecompleto";

            List<?> lista = sessao.createQuery(hql).list();

            for (int i = 0; i < lista.size(); i++) {
                Object[] row = (Object[]) lista.get(i);
                resultado.add(row[0] + "," + row[1]);
            }
            
        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

}
