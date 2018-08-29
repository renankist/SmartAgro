/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPreUpdateEventListenerImpl;

/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversPreUpdateEventListenerImpl extends EnversPreUpdateEventListenerImpl {
    
    public MyEnversPreUpdateEventListenerImpl(EnversService enversService) {
        super(enversService);
    }
    
}
