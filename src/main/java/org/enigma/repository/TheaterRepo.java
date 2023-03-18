package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Theater;

import java.util.List;

public class TheaterRepo extends Repo implements IRepo<Theater>{
    public TheaterRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void create(Theater theater) {
        inTransaction(em -> {
            em.persist(theater);
        });
    }

    @Override
    public void update(Theater theater) {
        inTransaction(em -> {
            em.merge(theater);
        });
    }

    @Override
    public void delete(Theater theater) {
        inTransaction(em -> {
            em.remove(theater);
        });
    }

    @Override
    public List<Theater> getAll(int page, int size) {
        TypedQuery<Theater> result = entityManager.createNamedQuery("find all theater", Theater.class);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);
        List<Theater> theaters = result.getResultList();
        return theaters;
    }

    @Override
    public Theater getById(int id) {
        TypedQuery<Theater> result = entityManager.createNamedQuery("find by id theater", Theater.class);
        result.setParameter("id", id);
        Theater theater = result.getSingleResult();
        return theater;
    }

    public Theater getIdFilm(int id) {
        TypedQuery<Theater> result = entityManager.createQuery("Select th from Theater th where th.film.filmId = ?1", Theater.class);
        result.setParameter(1, id);
        Theater theater = result.getSingleResult();
        return theater;
    }
}
