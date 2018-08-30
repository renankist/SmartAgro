/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import java.util.EventListener;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
import org.hibernate.envers.event.spi.EnversPostCollectionRecreateEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostDeleteEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPostInsertEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionRemoveEventListenerImpl;
import org.hibernate.envers.event.spi.EnversPreCollectionUpdateEventListenerImpl;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 *
 * @author Renan Luis Kist
 */
public class MyEnversIntegrator implements Integrator {

 

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        
        
        final EnversService enversService = serviceRegistry.getService(EnversService.class);
        final EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        
        listenerRegistry.addDuplicationStrategy(EnversListenerDuplicationStrategy.INSTANCE);
        

        
        if (enversService.getEntitiesConfigurations().hasAuditedEntities()) {
            listenerRegistry.appendListeners(
                    EventType.POST_DELETE,
                    new MyEnversPostDeleteEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.POST_INSERT,
                    new MyEnversPostInsertEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.PRE_UPDATE,
                    new MyEnversPreUpdateEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.POST_UPDATE,
                    new MyEnversPostUpdateEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.POST_COLLECTION_RECREATE,
                    new EnversPostCollectionRecreateEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.PRE_COLLECTION_REMOVE,
                    new EnversPreCollectionRemoveEventListenerImpl(enversService)
            );
            listenerRegistry.appendListeners(
                    EventType.PRE_COLLECTION_UPDATE,
                    new EnversPreCollectionUpdateEventListenerImpl(enversService)
            );
        }
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sfi, SessionFactoryServiceRegistry sfsr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
