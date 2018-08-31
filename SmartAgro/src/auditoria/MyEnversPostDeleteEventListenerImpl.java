/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
import org.hibernate.event.spi.PostDeleteEvent;
import telas.FrmPrincipal;


/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversPostDeleteEventListenerImpl extends EnversPostDeleteEventListenerImpl{
  
     
    public MyEnversPostDeleteEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
    
    @Override
    public void onPostDelete(PostDeleteEvent event) {
        if (FrmPrincipal.config.getAuditorialigada() == false) {
            return;
        }

        super.onPostDelete(event);
    }
}
