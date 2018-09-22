package com.blackdev.bestCoffee.coffee;

import com.blackdev.bestCoffee.coffee.enums.CoffeeBurn;
import com.blackdev.bestCoffee.coffee.enums.CoffeeMilling;
import com.blackdev.bestCoffee.coffee.enums.CoffeeSpecies;
import com.blackdev.bestCoffee.rating.Rating;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "coffees")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoffeeSpecies species;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoffeeMilling milling;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CoffeeBurn burn;

    @OneToMany(mappedBy = "coffee", cascade = CascadeType.ALL)
    private Collection<Rating> ratings;

    @Column(nullable = false)
    private String image;

    private String body;
    private String flavour;
    private String aftertaste;
    private int aroma;
    private int acidity;
    private int intensity;

    public Coffee() {
        this.ratings = new ArrayList<>();
    }

    public Coffee(String name, CoffeeSpecies species, CoffeeMilling milling, CoffeeBurn burn, Collection<Rating> ratings, String image, String body, String flavour, String aftertaste, int aroma, int acidity, int intensity) {
        this.name = name;
        this.species = species;
        this.milling = milling;
        this.burn = burn;
        this.ratings = ratings;
        this.image = image;
        this.body = body;
        this.flavour = flavour;
        this.aftertaste = aftertaste;
        this.aroma = aroma;
        this.acidity = acidity;
        this.intensity = intensity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFlavour() {
        return flavour;
    }

    public void setFlavour(String flavour) {
        this.flavour = flavour;
    }

    public String getAftertaste() {
        return aftertaste;
    }

    public void setAftertaste(String aftertaste) {
        this.aftertaste = aftertaste;
    }

    public CoffeeSpecies getSpecies() {
        return species;
    }

    public void setSpecies(CoffeeSpecies species) {
        this.species = species;
    }

    public CoffeeMilling getMilling() {
        return milling;
    }

    public void setMilling(CoffeeMilling milling) {
        this.milling = milling;
    }

    public CoffeeBurn getBurn() {
        return burn;
    }

    public void setBurn(CoffeeBurn burn) {
        this.burn = burn;
    }

    public int getAroma() {
        return aroma;
    }

    public void setAroma(int aroma) {
        this.aroma = aroma;
    }

    public int getAcidity() {
        return acidity;
    }

    public void setAcidity(int acidity) {
        this.acidity = acidity;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public Collection<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Collection<Rating> ratings) {
        this.ratings = ratings;
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
        Coffee coffee = (Coffee) o;
        return id == coffee.id &&
                aroma == coffee.aroma &&
                acidity == coffee.acidity &&
                intensity == coffee.intensity &&
                Objects.equals(name, coffee.name) &&
                species == coffee.species &&
                milling == coffee.milling &&
                burn == coffee.burn &&
                Objects.equals(ratings, coffee.ratings) &&
                Objects.equals(image, coffee.image) &&
                Objects.equals(body, coffee.body) &&
                Objects.equals(flavour, coffee.flavour) &&
                Objects.equals(aftertaste, coffee.aftertaste);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, species, milling, burn, ratings, image, body, flavour, aftertaste, aroma, acidity, intensity);
    }
}
