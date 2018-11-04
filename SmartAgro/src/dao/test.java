/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;

/**
 *
 * @author renan
 */
public class test {
    public static void main(String args[]){
        
        BigDecimal b;
        
        b = new GraficoDAO().valorTotalVendidoNoMes();
                
        System.out.println(b);
        
        
        
    }
}
