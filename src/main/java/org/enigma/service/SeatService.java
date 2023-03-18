package org.enigma.service;

import org.enigma.model.Seat;
import org.enigma.model.Theater;
import org.enigma.repository.SeatRepo;

import java.util.List;

public class SeatService implements IService<Seat>{
    private SeatRepo seatRepo;
    private TheaterService theaterService;

    public SeatService(SeatRepo seatRepo, TheaterService theaterService) {
        this.seatRepo = seatRepo;
        this.theaterService = theaterService;
    }

    @Override
    public void create(Seat seat) {
        try {

            Integer getIdTheater = seat.getTheater().getTheaterId();
            Theater theater = this.theaterService.getById(getIdTheater);
            int stockAwal = theater.getStock();
            theater.setStock(stockAwal + 1);

            seat.setTheater(theater);

            seatRepo.create(seat);
            System.out.println("Success to create Seat");
            getAll(1,1000);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(Seat seat) {
        try {
            seatRepo.update(seat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            Seat seat = getById(id);
            seatRepo.delete(seat);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Seat> getAll(int page, int size) {
        List<Seat> seats = null;
        try {
            seats = seatRepo.getAll(page, size);
            seats.forEach(System.out::println);
            if (seats.isEmpty()) {
                System.err.println("List Seat is empty");
                seats = null;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return seats;
    }

    @Override
    public Seat getById(int id) {
        Seat seat = null;
        try {
            seat = seatRepo.getById(id);
        } catch (Exception e) {
//            System.err.println(e.getMessage());
        }
        if (seat == null) {
            System.err.println("id seat not found");
        }
        return seat;
    }

    public Seat getIdTheater(int id) {
        Seat seat = null;
        try {
            seat = seatRepo.getIdTheater(id);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return seat;
    }
}
