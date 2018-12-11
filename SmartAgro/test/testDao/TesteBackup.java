/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testDao;

import javax.swing.JDesktopPane;
import telas.IfrmBackup;

/**
 *
 * @author renan
 */
public class TesteBackup {
    public static void main(String args[]){
        
        JDesktopPane area = new JDesktopPane();
        area.setSize(200, 300);
        area.setVisible(true);
        IfrmBackup b = new IfrmBackup(); 
        
        area.add(b);
        
        b.setVisible(true);
        
        
    }
}
