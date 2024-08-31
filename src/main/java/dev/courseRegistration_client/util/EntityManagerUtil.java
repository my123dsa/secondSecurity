package dev.courseRegistration_client.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class EntityManagerUtil {

    public static EntityManager getEntityManager() {
        return EntityManagerFactorySingleton.getInstance().createEntityManager();
    }

    public static EntityTransaction getTransaction(EntityManager entityManager) {
        return entityManager.getTransaction();
    }
}
