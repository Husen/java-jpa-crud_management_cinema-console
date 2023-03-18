package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Seat;
import org.enigma.model.Theater;

import java.lang.reflect.Type;
import java.util.List;

public class SeatRepo extends Repo implements IRepo<Seat>{
    public SeatRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void create(Seat seat) {
        inTransaction(em -> {
            em.persist(seat);
        });
    }

    @Override
    public void update(Seat seat) {
        inTransaction(em -> {
            em.merge(seat);
        });
    }

    @Override
    public void delete(Seat seat) {
        inTransaction(em -> {
            em.remove(seat);
        });
    }

    @Override
    public List<Seat> getAll(int page, int size) {
        TypedQuery<Seat> result = entityManager.createNamedQuery("find all seat", Seat.class);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);
        List<Seat> seats = result.getResultList();
        return seats;
    }

    @Override
    public Seat getById(int id) {
        TypedQuery<Seat> result = entityManager.createNamedQuery("find by id seat", Seat.class);
        result.setParameter("id", id);
        Seat seat = result.getSingleResult();
        return seat;
    }

    public Seat getIdTheater(int id) {
        TypedQuery<Seat> result = entityManager.createQuery("Select st from Seat st where st.theater.theaterId = ?1", Seat.class);
        result.setParameter(1, id);
        Seat seat = result.getSingleResult();
        return seat;
    }
}
