/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import apoio.HibernateUtil;
import entidade.Produto;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author renan
 */
public class ProdutoDAO extends GenericDAO {
    
    private static final Logger logger = Logger.getLogger(GenericDAO.class);
            
    public ArrayList<Produto> consultarComEstoque(String descricao) {

        ArrayList<Produto> resultado = new ArrayList(); 

        Session sessao = null;
        try {

            sessao = HibernateUtil.getSessionFactory().openSession();
            sessao.beginTransaction();

            org.hibernate.Query q = sessao.createQuery("from Produto where quantidadeestoque > 0 AND upper(descricao) like upper(:desc) ");
            
            q.setString("desc", "%"+descricao+"%");
              
            resultado = (ArrayList) q.list();

        } catch (HibernateException he) {
            he.printStackTrace();
            logger.error("Erro ao consultar registros", he);
        } finally {
            sessao.close();
        }

        return resultado;

    }
    
    
    
}
