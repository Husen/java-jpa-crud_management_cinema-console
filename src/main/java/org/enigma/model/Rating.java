package org.enigma.model;

import jakarta.persistence.*;
import org.enigma.utils.ERating;

import java.util.List;

@Entity
@Table(name = "t_rating")
@NamedQueries({
        @NamedQuery(name = "find all rating", query = "select r from Rating r order by r.ratingId asc"),
        @NamedQuery(name = "find by id rating", query = "select r from Rating r where r.ratingId = :id")
})
public class Rating {
    @Id
    @SequenceGenerator(name="webuser_idwebuser_seq",
            sequenceName="webuser_idwebuser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="webuser_idwebuser_seq")
    @Column(name = "id", updatable=false)
    private int ratingId;

    @Column(name = "code", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERating code;

    @Column(name = "description")
    private String desciptionRating;

    @OneToMany(mappedBy = "rating")
    private List<Film> films;

    public Rating() {
    }

    public Rating(ERating code, String desciptionRating) {
        this.code = code;
        this.desciptionRating = desciptionRating;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public ERating getCode() {
        return code;
    }

    public void setCode(ERating code) {
        this.code = code;
    }

    public String getDesciptionRating() {
        return desciptionRating;
    }

    public void setDesciptionRating(String desciptionRating) {
        this.desciptionRating = desciptionRating;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", code=" + code +
                ", desciptionRating='" + desciptionRating + '\'' +
//                ", films=" + films +
                '}';
    }
}
