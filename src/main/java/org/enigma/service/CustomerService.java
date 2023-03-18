package org.enigma.service;

import org.enigma.model.Customer;
import org.enigma.model.Film;
import org.enigma.repository.CustomerRepo;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IService<Customer>{
    private CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public void create(Customer customer) {
        try {
            customerRepo.create(customer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            customerRepo.update(customer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        // ga dipake
    }

    @Override
    public List<Customer> getAll(int page, int size) {
        List<Customer> customers = null;
        try {
            customers = customerRepo.getAll(page, size);
            customers.forEach(System.out::println);
            if (customers.isEmpty()) {
                System.err.println("List Customer is empty");
                customers = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        Customer customer = null;
        try {
            customer = customerRepo.getById(id);
        } catch (Exception e) {
//            System.err.println(e.getMessage());
        }
        if (customer == null) {
            System.err.println("id not found");
        }
        return customer;
    }
}
