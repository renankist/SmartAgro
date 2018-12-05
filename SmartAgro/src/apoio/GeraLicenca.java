/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import static apoio.RSAcriptografia.LOCAL_CHAVE_PRIVADA;
import static apoio.RSAcriptografia.LOCAL_CHAVE_PUBLICA;
import static apoio.RSAcriptografia.criptografa;
import static apoio.RSAcriptografia.decriptografa;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import entidade.Licenca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

/**
 *
 * @author renan
 */
public class GeraLicenca {
    
    
    public static boolean criaLicenca(Licenca licenca){
        boolean retorno = false; 
          try {
            File arquivo = new File("smartagro.licenca");
            
            if(arquivo.exists()){
                arquivo.delete();
            }
            
            XStream xstream = new XStream();

            
            ObjectInputStream inputStream = null;
            
            inputStream = new ObjectInputStream(new FileInputStream(RSAcriptografia.LOCAL_CHAVE_PUBLICA));
            
            final PublicKey chavePublica = (PublicKey) inputStream.readObject();
            final byte[] textoCriptografado = RSAcriptografia.criptografa(xstream.toXML(licenca), chavePublica);
            
            FileOutputStream in = new FileOutputStream(arquivo);
            in.write(textoCriptografado);
            in.close();
            retorno = true; 
            
        } catch (Exception e) {
            System.out.println(e);
        }
          
          return retorno;
    }
    
    public static String verificaLicenca() {
        try {
            XStream xstream = new XStream(new DomDriver());

            Path arquivo = Paths.get("smartagro.licenca");

            byte[] textoCriptografado = Files.readAllBytes(arquivo);

            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(RSAcriptografia.LOCAL_CHAVE_PRIVADA));
            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();

                final String textoPuro = RSAcriptografia.decriptografa(textoCriptografado, chavePrivada);
                Licenca licenca = (Licenca) xstream.fromXML(textoPuro);
                System.out.println(licenca.getValidade());
                if (licenca.getValidade().before(new Date())) {
                    return "Licença expirada em: " + Formatacao.DataDMA(licenca.getValidade());
                } else {
                    long dt = (licenca.getValidade().getTime() - new Date().getTime());
                    Long dias = dt / 86400000L; // número de dias
                    int diasInteiros = dias.intValue();
                    if (diasInteiros <= 2 && diasInteiros > 0) {
                        return "Resta(m) " + diasInteiros + " dia(s) para a licença expirar! Solicite renovação.";
                    } else if (diasInteiros == 0) {
                        return "Último dia de licença! Solicite renovação.";
                    } else {
                        return "";
                    }
                }
            
        } catch (Exception e) {
            System.out.println(e);
            return "Falha ao verificar Licença!";
        }
    }
    
    
}
