/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.configuration.spi.AuditConfiguration;
import org.hibernate.envers.event.spi.EnversIntegrator;
import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

/**
 *
 * @author renan
 */
public class MyIntegrator extends EnversIntegrator {

    private AuditConfiguration enversConfiguration;

    @Override
    public void integrate(Configuration configuration, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        final EventListenerRegistry listenerRegistry = serviceRegistry.getService(EventListenerRegistry.class);
        listenerRegistry.addDuplicationStrategy(EnversListenerDuplicationStrategy.INSTANCE);
        enversConfiguration = AuditConfiguration.getFor(configuration, serviceRegistry.getService(ClassLoaderService.class));
        if (enversConfiguration.getEntCfg().hasAuditedEntities()) {
            listenerRegistry.prependListeners(EventType.POST_INSERT, new CustomEnversPostInsertEventListener(enversConfiguration));
            listenerRegistry.prependListeners(EventType.POST_UPDATE, new CustomEnversPostUpdateEventListener(enversConfiguration));
            listenerRegistry.prependListeners(EventType.POST_DELETE, new CustomEnversPostDeleteEventListener(enversConfiguration));
        }
    }

    

}
