package org.enigma.repository;

import jakarta.persistence.EntityManager;

import java.util.function.Consumer;

public class Repo {
    EntityManager entityManager;

    public Repo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected void inTransaction(Consumer<EntityManager> consumer) {
        try {
            entityManager.getTransaction().begin();

            // delete colomn this if record exist
//            entityManager.createNativeQuery("ALTER SEQUENCE webuser_idwebuser_seq RESTART WITH 1;").executeUpdate();
            // end condition

            consumer.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            entityManager.getTransaction().rollback();
        }
    }
}
