
import apoio.RSAcriptografia;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author renan
 */
public class TestLicenca {

    public static void main(String args[]) {

//       Licenca licenca = new Licenca();
//        licenca.setAgropecuaria("seu cuca é eu");
//        Date validade = null;
//        try {
//            validade = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss").parse("01/01/2018 - 23:59:59");
//        } catch (Exception e) {
//        }
//        
//        licenca.setValidade(validade);
//        licenca.setTipo("Anual");
        //CriptografiaRSA.geraChave();
        // RSacriptografia.geraLicenca(licenca);
        //CriptografiaRSA.verificaLicenca();
        String verificaLicenca = RSAcriptografia.verificaLicenca();
        if (verificaLicenca.contains("Erro") || verificaLicenca.contains("expirada") || verificaLicenca.contains("Falha")) {
            JOptionPane.showMessageDialog(null, verificaLicenca, "Licença", JOptionPane.OK_OPTION);
            System.exit(0);
        } else if (verificaLicenca.contains("Resta(m)") || verificaLicenca.contains("Último")) {
            JOptionPane.showMessageDialog(null, verificaLicenca, "Licença", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
