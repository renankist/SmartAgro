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

/**
 *
 * @author Renan Luis Kist
 */
public class CustomRevisionEntityListener implements RevisionListener {

    public void newRevision(Object revisionEntity) {
        CustomRevisionEntity customRevisionEntity
                = (CustomRevisionEntity) revisionEntity;

        if (FrmPrincipal.usuario != null) {
            customRevisionEntity.setUsername(FrmPrincipal.usuario.getUsuario());
        } else {
            customRevisionEntity.setUsername("login");
        }

        customRevisionEntity.setHora(new Date());

        try {
            customRevisionEntity.setIp(InetAddress.getLocalHost().getHostAddress());
        } catch (Exception e) {

        }
    }
}
