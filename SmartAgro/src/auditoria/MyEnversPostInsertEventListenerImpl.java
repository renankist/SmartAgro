/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.event.spi.PostInsertEvent;
import telas.FrmPrincipal;

/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversPostInsertEventListenerImpl extends EnversPostInsertEventListenerImpl{

    
    
    public MyEnversPostInsertEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
    
    
     @Override
    public void onPostInsert(PostInsertEvent event) {
        if (FrmPrincipal.config.getAuditorialigada() == false) {
            return;
        }

        super.onPostInsert(event);
    }
}
