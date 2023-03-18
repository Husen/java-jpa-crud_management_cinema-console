package org.enigma.view;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.controller.*;
import org.enigma.repository.*;
import org.enigma.service.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner input = new Scanner(System.in);
    static EntityManager entityManager = Factory.start();

    // repository
    // customer
    static CustomerRepo customerRepo = new CustomerRepo(entityManager);
    // rating
    static RatingRepo ratingRepo = new RatingRepo(entityManager);
    // film
    static FilmRepo filmRepo = new FilmRepo(entityManager);
    // theater
    static TheaterRepo theaterRepo = new TheaterRepo(entityManager);
    // seat
    static SeatRepo seatRepo = new SeatRepo(entityManager);
    // ticket
    static TicketTrxRepo ticketTrxRepo = new TicketTrxRepo(entityManager);


    // service
    // customer
    static CustomerService customerService = new CustomerService(customerRepo);
    // rating
    static RatingService ratingService = new RatingService(ratingRepo);
    // film
    static FilmService filmService = new FilmService(filmRepo);
    // theater
    static TheaterService theaterService = new TheaterService(theaterRepo);
    // seat
    static SeatService seatService = new SeatService(seatRepo, theaterService);
    // ticket
    static TicketTrxService ticketTrxService = new TicketTrxService(customerRepo, ticketTrxRepo);

    // controller
    // customer
    static CustomerController customerController = new CustomerController(customerService);
    // rating
    static RatingController ratingController = new RatingController(ratingService);
    // film
    static FilmController filmController = new FilmController(filmService, ratingService, theaterService);
    // theater
    static TheaterController theaterController = new TheaterController(theaterService, filmService, seatService);
    // seat
    static SeatController seatController = new SeatController(seatService, theaterService, ticketTrxService);
    // ticket
    static TicketTrxController ticketTrxController = new TicketTrxController(ticketTrxService);


    public void menu() {
        boolean run = true;
        while (run) {
            System.out.println("\n-----------------------------------");
            System.out.println(">> Welcome in System Cinema Ciawi <<");
            System.out.println("-----------------------------------");
            System.out.println("1. Manage Customer");
            System.out.println("2. Manage Film");
            System.out.println("3. Manage Theater");
            System.out.println("4. Manage Chair");
            System.out.println("5. Manage Rating on Film");
            System.out.println("6. Transaction");
            System.out.println("Enter your Choice : ");
            int selectMenu;
            try {
                selectMenu = input.nextInt();
                switch (selectMenu) {
                    case 1 -> customerController.manageCustomer();
                    case 2 -> filmController.manageFilm();
                    case 3 -> theaterController.manageTheater();
                    case 4 -> seatController.manageSeat();
                    case 5 -> ratingController.manageRating();
                    case 6 -> ticketTrxController.transacation();
                }
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
