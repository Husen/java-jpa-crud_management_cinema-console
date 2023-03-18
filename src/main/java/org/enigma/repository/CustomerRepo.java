package org.enigma.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.enigma.model.Customer;

import java.util.List;

public class CustomerRepo extends Repo implements IRepo<Customer> {
    public CustomerRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public Customer create2(Customer customer) {
        inTransaction(em -> {
            em.persist(customer);
            em.flush(); // untuk mengembalikan objek
        });
        return customer;
    }

    @Override
    public void create(Customer customer) {
        inTransaction(em -> {
            em.persist(customer);
        });
    }

    @Override
    public void update(Customer customer) {
        inTransaction(em -> {
            em.merge(customer);
        });
    }

    @Override
    public void delete(Customer customer) {
        inTransaction(em -> {
            em.remove(customer);
        });
    }

    @Override
    public List<Customer> getAll(int page, int size) {
        TypedQuery<Customer> result = entityManager.createNamedQuery("find all customer", Customer.class);
        result.setFirstResult((page - 1) * size);
        result.setMaxResults(size);
        List<Customer> customers = result.getResultList();
        return customers;
    }

    @Override
    public Customer getById(int id) {
        TypedQuery<Customer> result = entityManager.createNamedQuery("find by id customer", Customer.class);
        result.setParameter("id", id);
        Customer customer = result.getSingleResult();
        return customer;
    }

    public Customer getLastData() {
        TypedQuery<Customer> result = entityManager.createQuery("select r from Customer r order by r.customerId desc", Customer.class);
        result.setMaxResults(1);
        Customer customer = result.getSingleResult();
        System.out.println(customer.toString());
        return customer;
    }
}
