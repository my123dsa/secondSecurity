package dev.courseRegistration_client.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static EntityManagerFactory instance;

    private EntityManagerFactorySingleton() { }

    public static synchronized EntityManagerFactory getInstance() {
        if (instance == null) {
            instance = Persistence.createEntityManagerFactory("demo");
        }
        return instance;
    }
}
