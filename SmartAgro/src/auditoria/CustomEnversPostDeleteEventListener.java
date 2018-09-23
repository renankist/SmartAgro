/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.configuration.spi.AuditConfiguration;
import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
import org.hibernate.event.spi.PostDeleteEvent;
import telas.FrmPrincipal;

/**
 *
 * @author renan
 */
public class CustomEnversPostDeleteEventListener extends EnversPostDeleteEventListenerImpl {
    
    public CustomEnversPostDeleteEventListener(AuditConfiguration enversConfiguration) {
        super(enversConfiguration);
    }
    
    @Override
    public void onPostDelete(PostDeleteEvent event) {
        if (FrmPrincipal.getParametros().getAuditorialigada() == false) {
            return;    
        }else {
            super.onPostDelete(event);
          
        }
    }
}
