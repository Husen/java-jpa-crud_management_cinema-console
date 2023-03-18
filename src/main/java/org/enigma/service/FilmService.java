package org.enigma.service;

import org.enigma.model.Film;
import org.enigma.repository.FilmRepo;

import java.util.ArrayList;
import java.util.List;

public class FilmService implements IService<Film>{
    private FilmRepo filmRepo;

    public FilmService(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @Override
    public void create(Film film) {
        try {
            filmRepo.create(film);
            System.out.println("Success to create Film");
            getAll(1,10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Film film) {
        try {
            filmRepo.update(film);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Film film = getById(id);
            filmRepo.delete(film);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Film> getAll(int page, int size) {
        List<Film> films = new ArrayList<>();
        try {
            films = filmRepo.getAll(page, size);
            films.forEach(System.out::println);
            if (films.isEmpty()) {
                System.err.println("List film is empty");
                films = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return films;
    }

    @Override
    public Film getById(int id) {
        Film film = null;
        try {
            film = filmRepo.getById(id);
        } catch (Exception e) {
//            System.err.println(e.getMessage());
        }
        if (film == null) {
            System.err.println("id film not found");
        }
        return film;
    }
}
