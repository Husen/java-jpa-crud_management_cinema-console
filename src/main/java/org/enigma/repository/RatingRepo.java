package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Rating;

import java.util.List;

public class RatingRepo extends Repo implements IRepo<Rating> {
    public RatingRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void create(Rating rating) {
        inTransaction(em -> {
            em.persist(rating);
        });
    }

    @Override
    public void update(Rating rating) {
        inTransaction(em -> {
            em.merge(rating);
        });
    }

    @Override
    public void delete(Rating rating) {
        inTransaction(em -> {
            em.remove(rating);
        });
    }

    @Override
    public List<Rating> getAll(int page, int size) {
        TypedQuery<Rating> result = entityManager.createNamedQuery("find all rating", Rating.class);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);
        List<Rating> ratings = result.getResultList();
        return ratings;
    }

    @Override
    public Rating getById(int id) {
        TypedQuery<Rating> result = entityManager.createNamedQuery("find by id rating", Rating.class);
        result.setParameter("id", id);
        Rating ratings = result.getSingleResult();
        return ratings;
    }
}
