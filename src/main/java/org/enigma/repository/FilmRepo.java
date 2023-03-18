package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Film;

import java.util.List;

public class FilmRepo extends Repo implements IRepo<Film>{
    public FilmRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void create(Film film) {
        inTransaction(em -> {
            em.persist(film);
        });
    }

    @Override
    public void update(Film film) {
        inTransaction(em -> {
            em.merge(film);
        });
    }

    @Override
    public void delete(Film film) {
        inTransaction(em -> {
            em.remove(film);
        });
    }

    @Override
    public List<Film> getAll(int page, int size) {
        TypedQuery<Film> result = entityManager.createNamedQuery("find all film", Film.class);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);
        List<Film> films = result.getResultList();
        return films;
    }

    @Override
    public Film getById(int id) {
        TypedQuery<Film> result = entityManager.createNamedQuery("find by id film", Film.class);
        result.setParameter("id", id);
        Film film = result.getSingleResult();
        return film;
    }
}
