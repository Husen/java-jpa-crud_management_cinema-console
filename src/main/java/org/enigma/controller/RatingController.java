package org.enigma.controller;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Rating;
import org.enigma.repository.RatingRepo;
import org.enigma.service.RatingService;
import org.enigma.view.Menu;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RatingController implements IController{
    private RatingService ratingService;
    Scanner input = new Scanner(System.in);

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    public void manageRating() {
        System.out.println("\n-----------------------------------");
        System.out.println(">> Manage Rating <<");
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
        System.out.println(">> Show Rating <<");
        System.out.println("-----------------------------------");
        System.out.println("1. Show All Data");
        System.out.println("2. Show By Id_Rating");
        System.out.println("3. Back");
        System.out.println("Enter your Choice : ");
        int selectShow;
        try {
            selectShow = input.nextInt();
            switch (selectShow) {
                case 1 -> getAll();
                case 2 -> getById();
                case 3 -> manageRating();
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void add() {
        // di create pake constructor di Main.java
    }

    @Override
    public void update() {
        System.out.println("Enter the id_Rating you want to update : ");
        int inIdRating;
        try {
            inIdRating = input.nextInt();
            Rating rating = ratingService.getById(inIdRating);
            if (rating == null) {
                manageRating();
            }
            System.out.println("Please Update data " + rating);
            System.out.println("Input description : " + input.nextLine());
            var inDescriptionRating = input.nextLine();
            Rating rating1 = new Rating();
            rating1.setCode(rating.getCode());
            rating1.setRatingId(inIdRating);
            rating1.setDesciptionRating(inDescriptionRating);
            ratingService.update(rating1);
            System.out.println("Success to update rating");
            System.out.println(ratingService.getById(inIdRating));
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void delete() {
//        System.out.println("Enter the id_Rating you want to delete : ");
//        int inIdRating;
//        try {
//            inIdRating = input.nextInt();
//            Rating rating = ratingService.getById(inIdRating);
//            if (rating == null) {
//                System.err.println("Failed to delete");
//                manageRating();
//            }
//            ratingService.delete(inIdRating);
//            System.out.println("Success to delete rating on id_Rating " + inIdRating);
//        } catch (InputMismatchException e) {
//            System.err.println(e.getMessage());
//        }
    }

    @Override
    public List<Rating> getAll() {
        List<Rating> ratings = ratingService.getAll(1, 10);
        return ratings;
    }

    @Override
    public Rating getById() {
        System.out.println("Enter the id_Rating you want to view : ");
        int inIdRating;
        Rating rating = new Rating();
        try {
            inIdRating = input.nextInt();
            rating = ratingService.getById(inIdRating);
            if (rating == null) {
                manageRating();
            }
            System.out.println(rating);
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return rating;
    }
}
