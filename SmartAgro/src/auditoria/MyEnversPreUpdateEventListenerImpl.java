/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;
import org.hibernate.event.spi.PreUpdateEvent;
import telas.FrmPrincipal;

/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversPreUpdateEventListenerImpl extends EnversPreUpdateEventListenerImpl {
    
   
    
    public MyEnversPreUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
    
   
     public boolean onPreUpdate(PreUpdateEvent event) {
         
        if (FrmPrincipal.config.getAuditorialigada() == false){
            return false;
        }
         
        return super.onPreUpdate(event);
    }
    
}
