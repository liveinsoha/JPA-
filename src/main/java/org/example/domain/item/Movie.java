package org.example.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("M")
@PrimaryKeyJoinColumn(name = "MOVIE_ID")
public class Movie extends Item {

    String director;
    String genre;

    protected Movie() {
    }

    public Movie(String director, String genre, int price) {
        this.director = director;
        this.genre = genre;
        super.price = price;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }


}
