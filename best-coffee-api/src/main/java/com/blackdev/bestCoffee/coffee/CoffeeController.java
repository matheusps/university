package com.blackdev.bestCoffee.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeService coffeeService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<Coffee>> getAll(){
        Collection<Coffee> data = this.coffeeService.getAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Coffee>> getById(@PathVariable("id") Long id){
        Optional<Coffee> data = this.coffeeService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @PostMapping()
    public ResponseEntity<Coffee> create(@RequestBody Coffee coffee){
        Coffee newCoffee = this.coffeeService.create(coffee);
        return new ResponseEntity<>(newCoffee, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole()")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Coffee> update(@PathVariable("id") long id, @RequestBody Coffee coffee){
        Coffee updatedCoffee = this.coffeeService.update(id, coffee);
        return (updatedCoffee != null)
                ? new ResponseEntity<>(updatedCoffee, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAnyRole()")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable("id") long id){
        boolean data = this.coffeeService.deleteById(id);
        return (data)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
