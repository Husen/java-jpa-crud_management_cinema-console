package org.enigma.controller;

import org.enigma.model.Customer;
import org.enigma.service.CustomerService;
import org.enigma.view.Menu;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CustomerController implements IController<Customer>{
    private CustomerService customerService;
    Scanner input = new Scanner(System.in);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void manageCustomer() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Manage Customer <<");
        System.out.println("-----------------------------------");
//        System.out.println("1. Create");
        System.out.println("1. Show");
        System.out.println("2. Update");
//        System.out.println("4. Delete");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectMenu;
        try {
            selectMenu = input.nextInt();
            Menu run = new Menu();
            switch (selectMenu) {
//                case 1 -> add();
                case 1 -> show();
                case 2 -> update();
//                case 4 -> delete();
                case 3 -> run.menu();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void show() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Show Customer <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Customer");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> manageCustomer();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        // add dibuat di tickettrxController
    }

    @Override
    public void update() {
        System.out.println("Enter the id_Customer you want to update : ");
        int inIdCustomer;
        try {
            inIdCustomer = input.nextInt();
            Customer customer = customerService.getById(inIdCustomer);
            if (customer == null) {
                manageCustomer();
            }
            System.out.println("Please Update data " + customer);
            System.out.println("Name Customer :" + input.nextLine());
            var inNameCustomer = input.nextLine();
            System.out.println("Birth Date Customer : ");
            var inBDateCustomer = input.nextLine();
            Customer customer1 = new Customer();
            customer1.setNameCustomer(inNameCustomer);
            customer1.setBirthDate(LocalDate.parse(inBDateCustomer));
            customerService.update(customer1);

        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        // ga dipake
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerService.getAll(1,1000);
        return customers;
    }

    @Override
    public Customer getById() {
        System.out.println("Enter the id_Customer you want to view");
        int inIDCustomer;
        Customer customer = new Customer();
        try {
            inIDCustomer = input.nextInt();
            customer = customerService.getById(inIDCustomer);
            if (customer == null) {
                manageCustomer();
            }
            System.out.println(customer);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return customer;
    }
}
