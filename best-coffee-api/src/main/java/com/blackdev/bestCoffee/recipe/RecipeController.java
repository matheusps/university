package com.blackdev.bestCoffee.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<Recipe>> getAll(){
        Collection<Recipe> data = this.recipeService.getAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Recipe>> getById(@PathVariable("id") Long id){
        Optional<Recipe> data = this.recipeService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<Recipe> create(@RequestBody Recipe recipe, @RequestParam long userId){
        Recipe newRecipe = this.recipeService.create(recipe, userId);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable("id") long id){
        boolean data = this.recipeService.deleteById(id);
        return (data)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
