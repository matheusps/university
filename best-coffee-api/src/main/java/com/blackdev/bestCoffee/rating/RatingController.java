package com.blackdev.bestCoffee.rating;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<Rating>> getAll(){
        Collection<Rating> data = this.ratingService.getAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Rating>> getById(@PathVariable("id") Long id){
        Optional<Rating> data = this.ratingService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping()
    public ResponseEntity<Rating> create(@RequestBody Rating rating, @RequestParam long userId, @RequestParam long coffeeId){
        Rating newRating = this.ratingService.create(rating, userId, coffeeId);
        return new ResponseEntity<>(newRating, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Rating> delete(@PathVariable("id") long id){
        boolean data = this.ratingService.deleteById(id);
        return (data)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
