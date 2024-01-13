package org.example.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {

    String author;
    String isbn;

    protected Book() {
    }

    public Book(String author, String isbn, int price) {
        this.author = author;
        this.isbn = isbn;
        super.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }


}
