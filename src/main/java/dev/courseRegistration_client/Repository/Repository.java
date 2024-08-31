package dev.courseRegistration_client.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Repository {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("demo");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    public EntityManager getEntityManager() {
        return manager;
    }

}
