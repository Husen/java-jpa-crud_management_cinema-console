package org.enigma.service;

import org.enigma.model.Theater;
import org.enigma.repository.TheaterRepo;

import java.util.ArrayList;
import java.util.List;

public class TheaterService implements IService<Theater>{
    private TheaterRepo theaterRepo;

    public TheaterService(TheaterRepo theaterRepo) {
        this.theaterRepo = theaterRepo;
    }

    @Override
    public void create(Theater theater) {
        try {
            theaterRepo.create(theater);
            System.out.println("Success to create Theater");
            getAll(1, 10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Theater theater) {
        try {
            theaterRepo.update(theater);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Theater theater = getById(id);
            theaterRepo.delete(theater);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Theater> getAll(int page, int size) {
        List<Theater> theaters = new ArrayList<>();
        try {
            theaters = theaterRepo.getAll(page, size);
            theaters.forEach(System.out::println);
            if (theaters.isEmpty()) {
                System.err.println("List Theater is empty");
                theaters = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return theaters;
    }

    @Override
    public Theater getById(int id) {
        Theater theater = null;
        try {
            theater = theaterRepo.getById(id);
        } catch (Exception e) {
//            System.err.println(e.getMessage());
        }
        if (theater == null) {
            System.err.println("id theater not found");
        }
        return theater;
    }

    public Theater getIdFilm(int id) {
        Theater theater = null;
        try {
            theater = theaterRepo.getIdFilm(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return theater;
    }
}
