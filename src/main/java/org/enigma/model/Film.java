package org.enigma.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "t_film")
@NamedQueries({
        @NamedQuery(name = "find all film", query = "select f from Film f order by f.filmId desc"),
        @NamedQuery(name = "find by id film", query = "select f from Film f where f.filmId = :id")
})
public class Film {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private int filmId;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "show_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate showDate;

    @Column(name = "price", nullable = false)
    private int Price;

    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @OneToMany(mappedBy = "film")
    private List<Theater> theaters;

    public Film() {
    }

    public Film(LocalDate showDate) {
        this.showDate = showDate;
    }

    public Film(int filmId, String title, int duration, LocalDate showDate, int price, Rating rating, List<Theater> theaters) {
        this.filmId = filmId;
        this.title = title;
        this.duration = duration;
        this.showDate = showDate;
        Price = price;
        this.rating = rating;
        this.theaters = theaters;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", showDate=" + showDate +
                ", Price=" + Price +
                ", rating=" + rating +
//                ", theaters=" + theaters +
                '}';
    }
}
