package com.blackdev.bestCoffee.user;

import com.blackdev.bestCoffee.address.Address;
import com.blackdev.bestCoffee.rating.Rating;
import com.blackdev.bestCoffee.recipe.Recipe;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A user must have a name")
    private String name;

    @Column(unique = true)
    @NotNull(message = "A user must have a username")
    private String username;

    @Column(unique = true)
    @NotNull(message = "A user must have a email")
    @Email
    private String email;

    @NotNull(message = "A user must have a password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<Rating> ratings;

    @OneToMany(targetEntity = Recipe.class, mappedBy = "createdBy", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Recipe> recipes;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
    private Collection<Address> addresses;

    private String image;


    public User() {
        this.ratings = new ArrayList<>();
        this.recipes = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public User(@NotNull(message = "A user must have a name") String name, @NotNull(message = "A user must have a username") String username, @NotNull(message = "A user must have a email") @Email String email, @NotNull(message = "A user must have a password") String password, Collection<Rating> ratings, Collection<Recipe> recipes, Collection<Address> addresses, String image) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.ratings = ratings;
        this.recipes = recipes;
        this.addresses = addresses;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<Rating> ratings) {
        this.ratings = ratings;
    }

    public Collection<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Collection<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(ratings, user.ratings) &&
                Objects.equals(recipes, user.recipes) &&
                Objects.equals(addresses, user.addresses) &&
                Objects.equals(image, user.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, username, email, password, ratings, recipes, addresses, image);
    }
}


