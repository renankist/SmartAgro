/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

//import dao.GraficoDAO;
import dao.GraficoDAO;
import entidade.Venda;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author renan
 */
public class java {

    public static void main(String args[]) {

        Calendar cal = Calendar.getInstance();

        System.out.println(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        GraficoDAO dao = new GraficoDAO();

        ArrayList<Venda> vendas = dao.vendasFinalizadasMesAtual();

        for (int x = 0; x < vendas.size(); x++) {
            //Venda v = (Venda) vendas.get(x);
            System.out.println(vendas.get(x).getId());
        }

        System.exit(0);

    }

}
