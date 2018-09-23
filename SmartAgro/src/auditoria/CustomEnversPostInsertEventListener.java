/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.configuration.spi.AuditConfiguration;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.event.spi.PostInsertEvent;
import telas.FrmPrincipal;

/**
 *
 * @author renan
 */
public class CustomEnversPostInsertEventListener extends EnversPostInsertEventListenerImpl {

    public CustomEnversPostInsertEventListener(AuditConfiguration enversConfiguration) {
        super(enversConfiguration);
    }

    @Override
    public void onPostInsert(PostInsertEvent event) {
        if (FrmPrincipal.getParametros().getAuditorialigada() == false) {
          
            return;
           
        }else {
            super.onPostInsert(event);
          
        }
    }
}
