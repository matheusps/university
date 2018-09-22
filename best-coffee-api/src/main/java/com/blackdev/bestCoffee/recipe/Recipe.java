package com.blackdev.bestCoffee.recipe;

import com.blackdev.bestCoffee.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecipeCategory category;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_users")
    @JsonBackReference
    private User createdBy;

    @Column
    @ElementCollection(targetClass = String.class)
    private Collection<String> ingredients;

    @Column
    @ElementCollection(targetClass = String.class)
    private Collection<String> steps;

    @Column(nullable = false)
    private String image;

    public Recipe() {
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
    }

    public Recipe(String name, RecipeCategory category, User createdBy, Collection<String> ingredients, Collection<String> steps, String image) {
        this.name = name;
        this.category = category;
        this.createdBy = createdBy;
        this.ingredients = ingredients;
        this.steps = steps;
        this.image = image;
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

    public RecipeCategory getCategory() {
        return category;
    }

    public void setCategory(RecipeCategory category) {
        this.category = category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Collection<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Collection<String> getSteps() {
        return steps;
    }

    public void setSteps(Collection<String> steps) {
        this.steps = steps;
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
        Recipe recipe = (Recipe) o;
        return id == recipe.id &&
                Objects.equals(name, recipe.name) &&
                category == recipe.category &&
                Objects.equals(createdBy, recipe.createdBy) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(steps, recipe.steps) &&
                Objects.equals(image, recipe.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, category, createdBy, ingredients, steps, image);
    }
}
