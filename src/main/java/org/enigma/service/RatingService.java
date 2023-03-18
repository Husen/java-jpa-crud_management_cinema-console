package org.enigma.service;

import org.enigma.model.Rating;
import org.enigma.repository.RatingRepo;

import java.util.ArrayList;
import java.util.List;

public class RatingService implements IService<Rating>{
    private RatingRepo ratingRepo;

    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public void create(Rating rating) {
        try {
            ratingRepo.create(rating);
            getAll(1,10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Rating rating) {
        try {
            ratingRepo.update(rating);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Rating rating1 = getById(id);
            ratingRepo.delete(rating1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Rating> getAll(int page, int size) {
        List<Rating> ratings = new ArrayList<>();
        try {
            ratings = ratingRepo.getAll(page, size);
            ratings.forEach(System.out::println);
            if(ratings.isEmpty()) {
                System.err.println("List rating is empty");
                ratings = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ratings;
    }

    @Override
    public Rating getById(int id) {
        Rating rating = null;
        try {
            rating = ratingRepo.getById(id);
        } catch (Exception e) {
//            System.err.println(e.getMessage());
        }
        if (rating == null) {
            System.err.println("id not found !");
        }
        return rating;
    }
}
