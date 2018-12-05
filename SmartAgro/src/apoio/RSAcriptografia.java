package apoio;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import entidade.Licenca;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import javax.crypto.Cipher;
import net.bytebuddy.asm.Advice;

/**
 *
 * @author renan
 */
public class RSAcriptografia {

    public static final String TIPO_ALGORITMO = "RSA";

    public static final String LOCAL_CHAVE_PRIVADA = "private.key";

    public static final String LOCAL_CHAVE_PUBLICA = "public.key";

    /**
     * Este método cria um par de chaves Privada e Pública de 1025 bytes e
     * armazena nos arquivos
     */
    public static void criaChaves() {
        try {

            final KeyPairGenerator geradorChaves = KeyPairGenerator.getInstance(TIPO_ALGORITMO);
            geradorChaves.initialize(3064);
            final KeyPair chave = geradorChaves.generateKeyPair();

            File arquivoChavePrivada = new File(LOCAL_CHAVE_PRIVADA);
            File arquivoChavePublica = new File(LOCAL_CHAVE_PUBLICA);

            if (arquivoChavePrivada.getParentFile() != null) {
                arquivoChavePrivada.getParentFile().mkdirs();
            }

            arquivoChavePrivada.createNewFile();

            if (arquivoChavePublica.getParentFile() != null) {
                arquivoChavePublica.getParentFile().mkdirs();
            }

            arquivoChavePublica.createNewFile();

            // Salva a Chave Pública no arquivo
            ObjectOutputStream chavePublicaOS = new ObjectOutputStream(
                    new FileOutputStream(arquivoChavePublica));
            chavePublicaOS.writeObject(chave.getPublic());
            chavePublicaOS.close();

            // Salva a Chave Privada no arquivo
            ObjectOutputStream chavePrivadaOS = new ObjectOutputStream(
                    new FileOutputStream(arquivoChavePrivada));
            chavePrivadaOS.writeObject(chave.getPrivate());
            chavePrivadaOS.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean verificaExistenciaChave() {

        File chavePrivada = new File(LOCAL_CHAVE_PRIVADA);
        File chavePublica = new File(LOCAL_CHAVE_PUBLICA);

        if (chavePrivada.exists() && chavePublica.exists()) {
            return true;
        }

        return false;
    }

    /**
     *
     * @param texto
     * @param chave 
     * @return BYTES
     */
    public static byte[] criptografa(String texto, PublicKey chave) {
        byte[] cipherText = null;

        try {
            final Cipher cipher = Cipher.getInstance(TIPO_ALGORITMO);
            // Criptografa o texto puro usando a chave privada
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            cipherText = cipher.doFinal(texto.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cipherText;
    }

    /**
     *
     * @param texto
     * @param chave
     * @return String
     */
    public static String decriptografa(byte[] texto, PrivateKey chave) {
        byte[] dectyptedText = null;

        try {
            final Cipher cipher = Cipher.getInstance(TIPO_ALGORITMO);
            // Decriptografa o texto puro usando a chave Privada
            cipher.init(Cipher.DECRYPT_MODE, chave);
            dectyptedText = cipher.doFinal(texto);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new String(dectyptedText);
    }
//
//    public static void criaLicenca(Licenca licenca) {
//        try {
//            File arquivo = new File("smartagro.licenca");
//            
//             XStream xstream = new XStream();
//
//            ObjectInputStream inputStream = null;
//            
//            inputStream = new ObjectInputStream(new FileInputStream(LOCAL_CHAVE_PUBLICA));
//            final PublicKey chavePublica = (PublicKey) inputStream.readObject();
//            final byte[] textoCriptografado = criptografa(xstream.toXML(licenca), chavePublica);
//            
//            FileOutputStream in = new FileOutputStream(arquivo);
//            in.write(textoCriptografado);
//            in.close();
//          
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    public static String verificaLicenca() {
//        try {
//            XStream xstream = new XStream(new DomDriver());
//
//            Path arquivo = Paths.get("smartagro.licenca");
//
//            byte[] textoCriptografado = Files.readAllBytes(arquivo);
//
//            ObjectInputStream inputStream = null;
//            inputStream = new ObjectInputStream(new FileInputStream(LOCAL_CHAVE_PRIVADA));
//            final PrivateKey chavePrivada = (PrivateKey) inputStream.readObject();
//
//                final String textoPuro = decriptografa(textoCriptografado, chavePrivada);
//                Licenca licenca = (Licenca) xstream.fromXML(textoPuro);
//                System.out.println(licenca.getValidade());
//                if (licenca.getValidade().before(new Date())) {
//                    return "Licença expirada em: " + Formatacao.DataDMA(licenca.getValidade());
//                } else {
//                    long dt = (licenca.getValidade().getTime() - new Date().getTime());
//                    Long dias = dt / 86400000L; // número de dias
//                    int diasInteiros = dias.intValue();
//                    if (diasInteiros <= 5 && diasInteiros > 0) {
//                        return "Resta(m) " + diasInteiros + " dia(s) para a licença expirar!";
//                    } else if (diasInteiros == 0) {
//                        return "Último dia de licença! Solicite renovação.";
//                    } else {
//                        return "";
//                    }
//                }
//            
//        } catch (Exception e) {
//            System.out.println(e);
//            return "Falha ao verificar Licença!";
//        }
//    }

}
