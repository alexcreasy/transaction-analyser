package io.narayana.nta.persistence;

import io.narayana.nta.CleanupRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;
import javax.persistence.EntityManager;

/**
 * @author Alex Creasy / acreasy@redhat.com /
 */
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnitityManagerProvider {

    private CleanupRegistry registry;

    public EntityManager getEntityManager() {
        return null;
    }

    public void beginTransaction() {

    }

    public void commitTransaction() {

    }

    public void rollbackTransaction() {

    }

    @PostConstruct
    protected void setup() {
        registry = new CleanupRegistry();
    }

    @PreDestroy
    protected void tearDown() {
        registry.cleanup();
    }
}
