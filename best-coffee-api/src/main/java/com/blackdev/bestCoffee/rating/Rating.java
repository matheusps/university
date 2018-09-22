package com.blackdev.bestCoffee.rating;


import com.blackdev.bestCoffee.coffee.Coffee;
import com.blackdev.bestCoffee.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_ratings_users")
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_ratings_coffees")
    private Coffee coffee;

    @Column(nullable = false)
    private int rating;

    private String note;

    public Rating() {
    }

    public Rating(User user, Coffee coffee, int rating, String note) {
        this.user = user;
        this.coffee = coffee;
        this.rating = rating;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id &&
                rating == rating1.rating &&
                Objects.equals(user, rating1.user) &&
                Objects.equals(coffee, rating1.coffee) &&
                Objects.equals(note, rating1.note);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, coffee, rating, note);
    }
}
