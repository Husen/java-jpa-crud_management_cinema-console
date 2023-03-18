package org.enigma.controller;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Customer;
import org.enigma.model.Seat;
import org.enigma.model.Theater;
import org.enigma.model.Ticket;
import org.enigma.repository.CustomerRepo;
import org.enigma.repository.TicketTrxRepo;
import org.enigma.service.CustomerService;
import org.enigma.service.TicketTrxService;
import org.enigma.view.Menu;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TicketTrxController implements IController<Ticket>{
    private TicketTrxService ticketTrxService;
    Scanner input = new Scanner(System.in);

    public TicketTrxController(TicketTrxService ticketTrxService) {
        this.ticketTrxService = ticketTrxService;
    }

    public void transacation() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Transaction Buy Ticket <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Buy");
        System.out.println("2. Cancel Order");
        System.out.println("3. Show");
//        System.out.println("3. Update");
//        System.out.println("4. Delete");
        System.out.println("4. Back");
        System.out.println("Enter your Choice : ");
        int selectMenu;
        try {
            selectMenu = input.nextInt();
            Menu run = new Menu();
            switch (selectMenu) {
                case 1 -> buy();
                case 2 -> cancelOrder();
                case 3 -> show();
//                case 3 -> update();
                case 4 -> run.menu();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void show() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Show Transaction <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Transaction");
        System.out.println("3. Back");
//        System.out.println("4. get last id customer");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> transacation();
                case 4 -> ticketTrxService.getLastData();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void buy() {
        System.out.println(">> Process Data Transaction <<");
        System.out.println("-----------------------------------");
        System.out.println("Name Customer :" + input.nextLine());
        var inNameCustomer = input.nextLine();
        System.out.println("Birth Date Customer : ");
        var inBDateCustomer = input.nextLine();

        Customer customer = new Customer();
        customer.setNameCustomer(inNameCustomer);
        customer.setBirthDate(LocalDate.parse(inBDateCustomer));
        Customer getCustomer = ticketTrxService.create2(customer);

        // get id customer yg paling terakhir
//        System.out.println(getCustomer);
//        ================================================
        // validasi ketentuan usia
        try {
            String date = getCustomer.getBirthDate().toString();
            System.out.println(date);
            Date now = new Date();
            LocalDate date1 = LocalDate.parse(date);
            LocalDate ynow = now.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int age = Period.between(date1,ynow).getYears();
            System.out.println(age);
            Seat seat = new Seat();
            if (age < 13) {
                // A
            } else if (age < 18) {
                // A, BO
            } else if (age < 20) {
                // A, BO, R
            } else {
                // A, BO, R, D
            }

//            LocalDate yearBD =
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

//        ================================================

        //if (getCustomer.getBirthDate())
        // validasi film muncul sesuai usia customer

        System.out.println("Select id seat on theater available movies :");
        var inIdSeat = input.nextInt();
        // validasi id yg valid
    }

    public void cancelOrder() {

    }

    @Override
    public void add() {


    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = ticketTrxService.getAll(1,1000);
        return tickets;
    }

    @Override
    public Ticket getById() {
        System.out.println("Enter the id_Transaction you want to view : ");
        int inIdTrx = 1;
        Ticket ticket = new Ticket();
        try {
            inIdTrx = input.nextInt();
            ticketTrxService.getById(inIdTrx);
            if (ticket == null) {
                transacation();
            }
            System.out.println(ticket);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return ticket;
    }
}
