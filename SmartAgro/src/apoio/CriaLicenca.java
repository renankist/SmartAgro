/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import entidade.Licenca;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author renan
 */
public class CriaLicenca {

    public static void main(String args[]) {

        Licenca l = new Licenca();
        l.setAgropecuaria("Agropecuaria Reis");
        l.setTipo("Anual");
        Date validade = null;
        
        try {
            validade = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").parse("10/12/2018 - 23:59:59");
            l.setValidade(validade);
        }catch (Exception e) {
         e.printStackTrace();
        }
        
        //RSAcriptografia.criaChaves();
        GeraLicenca.criaLicenca(l);
    }
}
