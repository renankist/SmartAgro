/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Cidade;
import entidade.Venda;
import java.math.BigDecimal;
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

    public ArrayList<String> vendaPorColaborador(Date inicio, Date fim) {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        Calendar cal = Calendar.getInstance();

        int mes = cal.get(Calendar.MONTH);
        mes += 1;

        String ultimo = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String primeiro = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT c.nomecompleto as vendedor, count(v.id) as quantVenda "
                    + " FROM Venda as v, Colaborador as c "
                    + " where c.id = v.vendedor and v.dia between '" + inicio + "' and '" + fim + "' "
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

    public BigDecimal valorTotalVendidoNoMes(Date inicio, Date fim) {

        BigDecimal retorno;

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT sum(valortotal) as valorTotal "
                    + " FROM Venda "
                    + " where dia between '" + inicio + "' and '" + fim + "' ";

            retorno = (BigDecimal) sessao.createQuery(hql).uniqueResult();

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }
    
        
        if(retorno == null){
          retorno = new BigDecimal(0);
        }
        return retorno;
    }

    public ArrayList<String> valorVendidoPorColaborador(Date inicio, Date fim) {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        Calendar cal = Calendar.getInstance();

        int mes = cal.get(Calendar.MONTH);
        mes += 1;

        String ultimo = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String primeiro = cal.get(Calendar.YEAR) + "/" + mes + "/" + cal.getActualMinimum(Calendar.DAY_OF_MONTH);

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT c.nomecompleto as vendedor, sum(v.valortotal) as quantVenda "
                    + " FROM Venda as v, Colaborador as c "
                    + " where c.id = v.vendedor and v.dia between '" + inicio + "' and '" + fim + "' "
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

    public ArrayList<String> quantidadeDeVendasNoAnoPorMes(Date inicio, Date fim) {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT EXTRACT(MONTH FROM dia) as mes, EXTRACT(YEAR FROM dia) as ano , count(id) as quantVenda "
                    + " FROM Venda where dia between '" + inicio + "' and '" + fim + "' "
                    + " group by EXTRACT(YEAR FROM dia), EXTRACT(MONTH FROM dia) order by EXTRACT(YEAR FROM dia), EXTRACT(MONTH FROM dia)";

            List<?> lista = sessao.createQuery(hql).list();

            for (int i = 0; i < lista.size(); i++) {
                Object[] row = (Object[]) lista.get(i);
                
                System.out.println(row[0] +" - " + row[1]+" - " +row[2]);
                
                switch (Integer.parseInt(row[0] + "")) {
                    case 1:
                        row[0] = "Jan";
                        break;
                    case 2:
                        row[0] = "Fev";
                        break;
                    case 3:
                        row[0] = "Mar";
                        break;
                    case 4:
                        row[0] = "Abr";
                        break;
                    case 5:
                        row[0] = "Mai";
                        break;
                    case 6:
                        row[0] = "Jun";
                        break;
                    case 7:
                        row[0] = "Jul";
                        break;
                    case 8:
                        row[0] = "Ago";
                        break;
                    case 9:
                        row[0] = "Set";
                        break;
                    case 10:
                        row[0] = "Out";
                        break;
                    case 11:
                        row[0] = "Nov";
                        break;
                    case 12:
                        row[0] = "Dez";
                        break;
                    default:
                        row[0] = "Inválido";
                        break;
                }

                resultado.add(row[0] + " " + row[1] + "," + row[2]);

            }

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }

    public ArrayList<String> valorTotalVendidoNoAno(Date inicio, Date fim) {

        ArrayList<String> resultado = new ArrayList();

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT EXTRACT(MONTH FROM dia) as mes, sum(valortotal) as quantVenda, EXTRACT(YEAR FROM dia) as mes "
                    + " FROM Venda where dia between '" + inicio + "' and '" + fim + "'"
                    + " group by EXTRACT(MONTH FROM dia), EXTRACT(YEAR FROM dia) order by EXTRACT(YEAR FROM dia) ,EXTRACT(MONTH FROM dia) ";

            List<?> lista = sessao.createQuery(hql).list();

            for (int i = 0; i < lista.size(); i++) {
                Object[] row = (Object[]) lista.get(i);

                switch (Integer.parseInt(row[0] + "")) {
                    case 1:
                        row[0] = "Jan";
                        break;
                    case 2:
                        row[0] = "Fev";
                        break;
                    case 3:
                        row[0] = "mar";
                        break;
                    case 4:
                        row[0] = "Abr";
                        break;
                    case 5:
                        row[0] = "Mai";
                        break;
                    case 6:
                        row[0] = "Jun";
                        break;
                    case 7:
                        row[0] = "Jul";
                        break;
                    case 8:
                        row[0] = "Ago";
                        break;
                    case 9:
                        row[0] = "Set";
                        break;
                    case 10:
                        row[0] = "Out";
                        break;
                    case 11:
                        row[0] = "Nov";
                        break;
                    case 12:
                        row[0] = "Dev";
                        break;
                    default:
                        row[0] = "Inválido";
                        break;
                }

                resultado.add(row[0]+" "+row[2]+ "," + row[1]);

            }

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    

    }

    public int quantidadeDeVendasrealizadas(Date inicio, Date fim) {

        int retorno = 0;

        Session sessao = null;

        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            String hql = "SELECT count(id) as valorTotal "
                    + " FROM Venda "
                    + " where dia between '" + inicio + "' and '" + fim + "' ";

            retorno = Integer.parseInt(sessao.createQuery(hql).uniqueResult().toString());

        } catch (HibernateException he) {
            throw new HibernateException("Erro ao tentar consultar as vendas.");
            // logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }
        System.out.println(retorno);
        return retorno;
    }

}
