package org.enigma.service;

import org.enigma.model.Customer;
import org.enigma.model.Ticket;
import org.enigma.repository.CustomerRepo;
import org.enigma.repository.TicketTrxRepo;

import java.util.List;

public class TicketTrxService implements IService<Ticket>{
    private CustomerRepo customerRepo;
    private TicketTrxRepo ticketTrxRepo;

    public TicketTrxService(CustomerRepo customerRepo, TicketTrxRepo ticketTrxRepo) {
        this.customerRepo = customerRepo;
        this.ticketTrxRepo = ticketTrxRepo;
    }

    public Customer create2(Customer customer) {
        customerRepo.create2(customer);
        return customer;
    }

    @Override
    public void create(Ticket params) {

    }

    @Override
    public void update(Ticket params) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Ticket> getAll(int page, int size) {
        return null;
    }

    @Override
    public Ticket getById(int id) {
        return null;
    }

    public Customer getLastData() {
        Customer customer = new Customer();
        try {
            customer = customerRepo.getLastData();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        System.out.println(customer);
        return customer;
    }

    public Ticket getIdSeat(int id) {
        Ticket ticket = null;
        try {
            ticket = ticketTrxRepo.getIdSeat(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ticket;
    }
}
