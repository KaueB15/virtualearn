package br.com.fafic.virtualearn.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;

public class EntityManagerConnection {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("virtualearn");
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if (Objects.isNull(entityManager)){
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

}
