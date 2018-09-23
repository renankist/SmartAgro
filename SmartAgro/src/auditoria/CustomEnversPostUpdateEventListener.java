/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.configuration.spi.AuditConfiguration;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;
import telas.FrmPrincipal;

/**
 *
 * @author renan
 */
public class CustomEnversPostUpdateEventListener extends EnversPostUpdateEventListenerImpl {

    public CustomEnversPostUpdateEventListener(AuditConfiguration enversConfiguration) {
        super(enversConfiguration);
    }
    
    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        if (FrmPrincipal.getParametros().getAuditorialigada() == false) {
            return;
        } else {
            super.onPostUpdate(event);
        }

    }
}
