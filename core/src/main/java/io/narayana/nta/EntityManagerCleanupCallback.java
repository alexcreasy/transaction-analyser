package io.narayana.nta;

import javax.persistence.EntityManager;

/**
 * @author Alex Creasy / acreasy@redhat.com /
 */
public class EntityManagerCleanupCallback implements CleanupCallback {

    private ThreadLocal<EntityManager> emThreadLocal;

    /**
     *
     * @param emThreadLocal
     * @throws NullPointerException
     */
    public EntityManagerCleanupCallback(ThreadLocal<EntityManager> emThreadLocal) throws NullPointerException {
        if (emThreadLocal == null)
            throw new NullPointerException();
        this.emThreadLocal = emThreadLocal;
    }

    /**
     *
     */
    @Override
    public void cleanup() {
        EntityManager em = emThreadLocal.get();
        if (em != null && em.isOpen())
            em.close();
        emThreadLocal.remove();
    }
}
