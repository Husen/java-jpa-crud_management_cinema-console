package org.enigma.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "t_theater")
@NamedQueries({
        @NamedQuery(name = "find all theater", query = "select t from Theater t order by t.theaterId asc"),
        @NamedQuery(name = "find by id theater", query = "select t from Theater t where t.theaterId = :id")
})
public class Theater {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private int theaterId;

    @Column(name = "thater_number", nullable = false, unique = true)
    private String theaterNumber;

    @Column(name = "stock")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @OneToMany(mappedBy = "theater")
    private List<Seat> seats;

    public Theater() {
    }

    public Theater(int theaterId, String theaterNumber, int stock, Film film, List<Seat> seats) {
        this.theaterId = theaterId;
        this.theaterNumber = theaterNumber;
        this.stock = stock;
        this.film = film;
        this.seats = seats;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterNumber() {
        return theaterNumber;
    }

    public void setTheaterNumber(String theaterNumber) {
        this.theaterNumber = theaterNumber;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "theaterId=" + theaterId +
                ", theaterNumber='" + theaterNumber + '\'' +
                ", stock=" + stock +
                ", film=" + film +
//                ", seats=" + seats +
                '}';
    }
}
