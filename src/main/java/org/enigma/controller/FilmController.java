package org.enigma.controller;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Film;
import org.enigma.model.Rating;
import org.enigma.model.Theater;
import org.enigma.repository.FilmRepo;
import org.enigma.repository.RatingRepo;
import org.enigma.repository.TheaterRepo;
import org.enigma.service.FilmService;
import org.enigma.service.RatingService;
import org.enigma.service.TheaterService;
import org.enigma.view.Menu;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FilmController implements IController<Film>{
    private FilmService filmService;
    private RatingService ratingService;
    private TheaterService theaterService;
    Scanner input = new Scanner(System.in);

    public FilmController(FilmService filmService, RatingService ratingService, TheaterService theaterService) {
        this.filmService = filmService;
        this.ratingService = ratingService;
        this.theaterService = theaterService;
    }

    public void manageFilm() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Manage Film <<");
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
        System.out.println(">> Show Film <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Film");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> manageFilm();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        System.out.println(">> Add Data Film <<");
        System.out.println("-----------------------------------");
        System.out.println("Name Film :" + input.nextLine());
        var inNameFilm = input.nextLine();
        System.out.println("Duration (Minute) :");
        var inDurationFilm = input.nextInt();
        System.out.println("Date Film :" + input.nextLine());
        var inShowDateFilm = input.nextLine();
        System.out.println("Price :");
        var inPrice = input.nextInt();
        System.out.println("==============================");
        ratingService.getAll(1, 1000);
        System.out.println("==============================");
        System.out.println("Select id Rating : ");
        var inIdRating = input.nextInt();

        Rating rating = ratingService.getById(inIdRating);
        while (rating == null) {
            System.out.println("==============================");
            ratingService.getAll(1, 1000);
            System.out.println("==============================");
            System.err.println("Re-enter the correct id_Rating");
            System.out.println("Select id Rating : ");
            inIdRating = input.nextInt();
            rating = ratingService.getById(inIdRating);
        }

        Film film = new Film();
        film.setTitle(inNameFilm);
        film.setDuration(inDurationFilm);
        film.setShowDate(LocalDate.parse(inShowDateFilm));
        film.setPrice(inPrice);

        Rating rating1 = new Rating();
        rating1.setRatingId(inIdRating);
        film.setRating(rating1);

        filmService.create(film);
    }

    @Override
    public void update() {
        System.out.println("Enter the id_Film you want to update : ");
        int inIdFilm;
        try {
            inIdFilm = input.nextInt();
            Film films = filmService.getById(inIdFilm);
            if (films == null) {
                manageFilm();
            }
            System.out.println("Please Update data " + films);
            System.out.println("New Name Film :" + input.nextLine());
            var inNameFilm = input.nextLine();
            System.out.println("New Duration (Minute) :");
            var inDurationFilm = input.nextInt();
            System.out.println("New Date Film :" + input.nextLine());
            var inShowDateFilm = input.nextLine();
            System.out.println("Price :");
            var inPrice = input.nextInt();
            System.out.println("==============================");
            ratingService.getAll(1, 10);
            System.out.println("==============================");
            System.out.println("Select new or old id_Rating : ");
            var inIdRating = input.nextInt();
            Film film = new Film();
            film.setTitle(inNameFilm);
            film.setDuration(inDurationFilm);
            film.setShowDate(LocalDate.parse(inShowDateFilm));
            film.setPrice(inPrice);

            Rating rating = ratingService.getById(inIdRating);
            while (rating == null) {
                System.out.println("==============================");
                ratingService.getAll(1, 1000);
                System.out.println("==============================");
                System.err.println("Re-enter the correct id_Rating");
                System.out.println("Select id Rating : ");
                inIdRating = input.nextInt();
                rating = ratingService.getById(inIdRating);
            }

            Rating rating1 = new Rating();
            rating1.setRatingId(inIdRating);
            film.setRating(rating1);
            filmService.update(film);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        System.out.println("Enter the id_Film you want to delete : ");
        int inIdFilm;
        try {
            inIdFilm = input.nextInt();
            Film film = filmService.getById(inIdFilm);
            if (film == null) {
                System.err.println("Failed to delete");
                manageFilm();
            }
            // validasi ke theater , apakah id digunakan, jika sedang digunakan maka delete is failed
            Theater theater = theaterService.getIdFilm(inIdFilm);
            if (theater != null) {
                System.err.println("Fail to delete film with id " + inIdFilm);
                System.err.println("id " + inIdFilm + " is in used by Theater");
            } else {
                filmService.delete(inIdFilm);
                System.out.println("Success to delete Film on id_Film " + inIdFilm);
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Film> getAll() {
        List<Film> films = filmService.getAll(1,1000);
        return films;
    }

    @Override
    public Film getById() {
        System.out.println("Enter the id_Film you want to view : ");
        int inIDFilm;
        Film film = new Film();
        try {
            inIDFilm = input.nextInt();
            film = filmService.getById(inIDFilm);
            if (film == null) {
                manageFilm();
            }
            System.out.println(film);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return film;
    }
}
