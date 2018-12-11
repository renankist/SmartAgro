/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renan
 */
public class TesteBackup {
    public static void main(String args[]){
        
        Backup b = new Backup("/home/renan/Desktop");
        
        try { 
            b.fazBackup();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesteBackup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
