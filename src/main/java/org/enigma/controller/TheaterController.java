package org.enigma.controller;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Film;
import org.enigma.model.Seat;
import org.enigma.model.Theater;
import org.enigma.repository.FilmRepo;
import org.enigma.repository.SeatRepo;
import org.enigma.repository.TheaterRepo;
import org.enigma.service.FilmService;
import org.enigma.service.SeatService;
import org.enigma.service.TheaterService;
import org.enigma.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TheaterController implements IController<Theater>{
    private TheaterService theaterService;
    private FilmService filmService;
    private SeatService seatService;
    Scanner input = new Scanner(System.in);

    public TheaterController(TheaterService theaterService, FilmService filmService, SeatService seatService) {
        this.theaterService = theaterService;
        this.filmService = filmService;
        this.seatService = seatService;
    }

    public void manageTheater() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Manage Theater <<");
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
        System.out.println(">> Show Theater <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Theater");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> manageTheater();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        List<Film> films = filmService.getAll(1,1000);
        if (films == null) {
            System.err.println("There is no data on the Film, please fill in the data file first");
            Menu run = new Menu();
            run.menu();
        }
        System.out.println(">> Add Data Film <<");
        System.out.println("-----------------------------------");
        System.out.println("Theater Number :" + input.nextLine());
        var inTitleTheater = input.nextLine();
        System.out.println("==============================");
        filmService.getAll(1, 1000);
        System.out.println("==============================");
        System.out.println("Select a film in the theater room (id_Film) :");
        var inIdFilm = input.nextInt();

        try {
            Film film = filmService.getById(inIdFilm);
            while (film == null) {
                System.out.println("==============================");
                filmService.getAll(1, 1000);
                System.out.println("==============================");
                System.out.println("Select a film in the theater room (id_Film) :");
                inIdFilm = input.nextInt();
                film = filmService.getById(inIdFilm);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }

        Theater theater = new Theater();
        theater.setTheaterNumber(inTitleTheater);
        theater.setStock(0);

        Film film1 = new Film();
        film1.setFilmId(inIdFilm);

        theater.setFilm(film1);

        theaterService.create(theater);
    }

    @Override
    public void update() {
        System.out.println("Enter the id_Theater you want to update : ");
        int inIdTheater;
        try {
            inIdTheater = input.nextInt();
            Theater theater = theaterService.getById(inIdTheater);
            if (theater == null) {
                manageTheater();
            }
            System.out.println("Please Update data " + theater);
            System.out.println("New Theater Number :" + input.nextLine());
            var inTitleTheater = input.nextLine();
            System.out.println("==============================");
            filmService.getAll(1, 1000);
            System.out.println("==============================");
            System.out.println("Select a film in the theater room (new or old id_Film) :");
            var inIdFilm = input.nextInt();

            Film film = filmService.getById(inIdFilm);
            while (film == null) {
                System.out.println("==============================");
                filmService.getAll(1, 1000);
                System.out.println("==============================");
                System.err.println("Re-enter the correct id_Rating");
                System.out.println("Select a film in the theater room (id_Film) :");
                inIdFilm = input.nextInt();
                film = filmService.getById(inIdFilm);
            }
            Theater theater1 = new Theater();
            theater1.setTheaterNumber(inTitleTheater);
            theater1.setStock(theater.getStock());

            Film film1 = new Film();
            film1.setFilmId(inIdFilm);
            theater1.setFilm(film1);

            theaterService.update(theater1);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println("Enter the id_Theater you want to delete : ");
        int inIdTheater;
        try {
            inIdTheater = input.nextInt();
            Theater theater = theaterService.getById(inIdTheater);
            if (theater == null) {
                System.err.println("Failed to delete");
                manageTheater();
            }
            // Validasi ke seat , apakah seat digunakan, jika sedang digunakan maka delete is failed
            Seat seat = seatService.getIdTheater(inIdTheater);
            if (seat != null) {
                System.err.println("Fail to delete Theater with id " + inIdTheater);
                System.err.println("id " + inIdTheater + " is in used by Seat");
                Menu run = new Menu();
                run.menu();
            } else {
                theaterService.delete(inIdTheater);
                System.out.println("Success to delete Theater on id_Theater " + inIdTheater);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Theater> getAll() {
        List<Theater> theaters = theaterService.getAll(1,1000);
        return theaters;
    }

    @Override
    public Theater getById() {
        System.out.println("Enter the id_Theater you want to view : ");
        int inIdTheater;
        Theater theater = new Theater();
        try {
            inIdTheater = input.nextInt();
            theaterService.getById(inIdTheater);
            if (theater == null) {
                manageTheater();
            }
            System.out.println(theater);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return theater;
    }
}
