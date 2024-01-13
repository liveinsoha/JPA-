package org.example.domain.item;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    String artist;

    protected Album() {
    }

    public Album(String artist, int price) {
        this.artist = artist;
        super.price = price;
    }

    public String getArtist() {
        return artist;
    }


}
