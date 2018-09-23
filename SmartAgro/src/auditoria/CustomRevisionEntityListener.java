/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import java.net.InetAddress;
import java.util.Date;
import org.hibernate.envers.RevisionListener;
import telas.FrmPrincipal;
import telas.jfrLogin;

/**
 *
 * @author Renan Luis Kist
 */
public class CustomRevisionEntityListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
       
            CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;

            customRevisionEntity.setUsername(jfrLogin.getUsuarioLogado().getUsuario());
            customRevisionEntity.setHora(new Date());

            try {
                customRevisionEntity.setIp(InetAddress.getLocalHost().getHostAddress());
            } catch (Exception e) {

            }
        
    }
}
