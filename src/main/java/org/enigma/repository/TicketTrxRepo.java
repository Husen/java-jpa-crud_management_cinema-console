package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Ticket;

import java.util.List;

public class TicketTrxRepo extends Repo implements IRepo<Ticket> {
    public TicketTrxRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void create(Ticket params) {

    }

    @Override
    public void update(Ticket params) {

    }

    @Override
    public void delete(Ticket params) {

    }

    @Override
    public List<Ticket> getAll(int page, int size) {
        return null;
    }

    @Override
    public Ticket getById(int id) {
        return null;
    }

    public Ticket getIdSeat(int id) {
        TypedQuery<Ticket> result = entityManager.createQuery("Select trx from Ticket trx where trx.seat.seatId = ?1",Ticket.class);
        result.setParameter(1,id);
        Ticket ticket = result.getSingleResult();
        return ticket;
    }
}
