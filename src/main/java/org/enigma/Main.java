package org.enigma;

import jakarta.persistence.EntityManager;
import org.enigma.config.Factory;
import org.enigma.model.Rating;
import org.enigma.repository.RatingRepo;
import org.enigma.service.RatingService;
import org.enigma.utils.ERating;
import org.enigma.view.Menu;

public class Main {
//    static EntityManager entityManager = Factory.start();
//    static RatingRepo ratingRepo = new RatingRepo(entityManager);
//    static RatingService ratingService = new RatingService(ratingRepo);

    public static void main(String[] args) {
        Menu run = new Menu();
        run.menu();


//        createRating();
    }

//    static void createRating() {
//        Rating rating1 = new Rating(ERating.A, "");
//        Rating rating2 = new Rating(ERating.BO, "");
//        Rating rating3 = new Rating(ERating.R, "");
//        Rating rating4 = new Rating(ERating.D, "");
//        ratingService.create(rating1);
//        ratingService.create(rating2);
//        ratingService.create(rating3);
//        ratingService.create(rating4);
//    }
}