/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.*;
import org.apache.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author Morgana
 */
public class Email {

    private static Logger logger = Logger.getLogger(Email.class);
    
    public static void enviarEmail(String para, String assunto, String mensagem, String arquivo) {
        
        final ParametrosEmail parametros = new ParametrosEmail(para, assunto, mensagem, arquivo);
        
        new Thread() {

            @Override
            public void run() {
                // Email de autenticação
                final String username = "morganabagatini@universo.univates.br";
                final String password = "@Wilde69";

                // Recipient's email ID needs to be mentioned.
                String to = parametros.getPara();

                // Sender's email ID needs to be mentioned
                String from = username;

                // Assuming you are sending email from localhost
                String host = "smtp.gmail.com";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.setProperty("mail.smtp.host", host);
                properties.setProperty("mail.smtp.socketFactory.port", "465");
                properties.setProperty("mail.smtp.port", "465");
                properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.setProperty("mail.smtp.auth", "true");

                // Get the default Session object.
                Authenticator aut = new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                };

                Session session = Session.getDefaultInstance(properties, aut);

                try {
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress("SmartAgro" + "<" + from + ">"));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject(parametros.getAssunto());

                    // Create the message part 
                    BodyPart messageBodyPart = new MimeBodyPart();

                    // Send the actual HTML message, as big as you like
                    messageBodyPart.setContent(parametros.getMensagem(), "text/html");

                    // Create a multipar message
                    Multipart multipart = new MimeMultipart();

                    // Set text message part
                    multipart.addBodyPart(messageBodyPart);

                    // Part two is attachment
                    if (!parametros.getArquivo().trim().isEmpty()) {
                        messageBodyPart = new MimeBodyPart();
                        String filename = parametros.getArquivo();
                        DataSource source = new FileDataSource(filename);
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(filename);
                        multipart.addBodyPart(messageBodyPart);
                    }

                    // Send the complete message parts
                    message.setContent(multipart);

                    // Send message
                    Transport.send(message);
                    System.out.println("E-mail enviado com sucesso!");
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                    logger.error("Erro ao enviar e-mail", mex);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }

}

class ParametrosEmail{
    
    private String para;
    private String assunto;
    private String mensagem;
    private String arquivo;

    public ParametrosEmail(String para, String assunto, String mensagem, String arquivo) {
        this.para = para;
        this.assunto = assunto;
        this.mensagem = mensagem;
        this.arquivo = arquivo;
    }
    
    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
}
