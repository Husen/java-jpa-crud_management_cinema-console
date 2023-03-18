package org.enigma.controller;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Seat;
import org.enigma.model.Theater;
import org.enigma.model.Ticket;
import org.enigma.repository.SeatRepo;
import org.enigma.repository.TheaterRepo;
import org.enigma.repository.TicketTrxRepo;
import org.enigma.service.SeatService;
import org.enigma.service.TheaterService;
import org.enigma.service.TicketTrxService;
import org.enigma.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SeatController implements IController<Seat>{
    private SeatService seatService;
    private TheaterService theaterService;
    private TicketTrxService ticketTrxService;
    Scanner input = new Scanner(System.in);

    public SeatController(SeatService seatService, TheaterService theaterService, TicketTrxService ticketTrxService) {
        this.seatService = seatService;
        this.theaterService = theaterService;
        this.ticketTrxService = ticketTrxService;
    }

    public void manageSeat() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Manage Seat <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Create");
        System.out.println("2. Show");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Back");
        System.out.println("Enter your Choice : ");
        int selectMenu;
        try {
            selectMenu = input.nextInt();
            Menu run = new Menu();
            switch (selectMenu) {
                case 1 -> add();
                case 2 -> show();
                case 3 -> update();
                case 4 -> delete();
                case 5 -> run.menu();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    public void show() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Show Seat <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Seat");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> manageSeat();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        List<Theater> theaters = theaterService.getAll(1,1000);
        if (theaters == null) {
            System.err.println("There is no data on the Theater, please fill in the data file first");
            Menu run = new Menu();
            run.menu();
        }
        System.out.println(">> Add Data Seat <<");
        System.out.println("-----------------------------------");
        System.out.println("Seat Number :" + input.nextLine());
        var inSeatNumber = input.nextLine();
        System.out.println("==============================");
        theaterService.getAll(1,1000);
        System.out.println("==============================");
        System.out.println("Select a film in the seat on theater (id_Theater) :");
        var inIdTheater = input.nextInt();

        try {
            Theater theater = theaterService.getById(inIdTheater);
            while (theater == null) {
                System.out.println("==============================");
                theaterService.getAll(1,1000);
                System.out.println("==============================");
                System.out.println("Select a film in the seat on theater (id_Theater) :");
                inIdTheater = input.nextInt();
                theater = theaterService.getById(inIdTheater);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }

        Seat seat = new Seat();
        seat.setSeatNumber(inSeatNumber);

        Theater theater1 = new Theater();
        theater1.setTheaterId(inIdTheater);

        seat.setTheater(theater1);

        seatService.create(seat);

    }

    @Override
    public void update() {
        System.out.println("Enter the id_Seat you want to update : ");
        int inIdSeat;
        try {
            inIdSeat = input.nextInt();
            Seat seat1 = seatService.getById(inIdSeat);
            if (seat1 == null) {
                manageSeat();
            }
            System.out.println("Please Update data " + seat1);
            System.out.println("New Seat Number :" + input.nextLine());
            var inSeatNumber = input.nextLine();
            System.out.println("==============================");
            theaterService.getAll(1,1000);
            System.out.println("==============================");
            System.out.println("Select a film in the seat on theater (id_Theater) :");
            var inIdTheater = input.nextInt();

            try {
                Theater theater = theaterService.getById(inIdTheater);
                while (theater == null) {
                    System.out.println("==============================");
                    theaterService.getAll(1,1000);
                    System.out.println("==============================");
                    System.out.println("Select a film in the seat on theater (id_Theater) :");
                    inIdTheater = input.nextInt();
                    theater = theaterService.getById(inIdTheater);
                }
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
            }

            Seat seat = new Seat();
            seat.setSeatNumber(inSeatNumber);

            Theater theater1 = new Theater();
            theater1.setTheaterId(inIdTheater);

            seat.setTheater(theater1);
            seatService.update(seat);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println("Enter the id_Seat you want to delete : ");
        int inIdSeat;
        try {
            inIdSeat = input.nextInt();
            Seat seat = seatService.getById(inIdSeat);
            if (seat == null) {
                System.err.println("Failed to delete");
                manageSeat();
            }
            // Validasi ke seat , apakah seat digunakan, jika sedang digunakan maka delete is failed
            Ticket ticket = ticketTrxService.getIdSeat(inIdSeat);
            if (ticket != null) {
                System.err.println("Fail to delete Seat with id " + inIdSeat);
                System.err.println("id " + inIdSeat + " is in used by Transaction ticket");
                Menu run = new Menu();
                run.menu();
            } else {
                seatService.delete(inIdSeat);
                System.out.println("Success to delete Seat on id_Seat " + inIdSeat);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Seat> getAll() {
        List<Seat> seats = seatService.getAll(1,1000);
        return seats;
    }

    @Override
    public Seat getById() {
        System.out.println("Enter the id_Seat you want to view : ");
        int inIdSeat;
        Seat seat = new Seat();
        try {
            inIdSeat = input.nextInt();
            theaterService.getById(inIdSeat);
            if (seat == null) {
                manageSeat();
            }
            System.out.println(seat);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return seat;
    }
}
