/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;
import telas.FrmPrincipal;

/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversPostUpdateEventListenerImpl extends EnversPostUpdateEventListenerImpl {



    public MyEnversPostUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        if (FrmPrincipal.config.getAuditorialigada() == false) {
            return;
        }

        super.onPostUpdate(event);
    }

}
