package com.blackdev.bestCoffee.owner;

import com.blackdev.bestCoffee.store.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<Owner>> getAll(){
        Collection<Owner> data = this.ownerService.getOwners();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Owner>> getById(@PathVariable("id") Long id){
        Optional<Owner> data = this.ownerService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value =  "/register")
    public ResponseEntity<Owner> create(@RequestBody Owner owner){
        Owner newOwner = this.ownerService.create(owner);
        return new ResponseEntity<>(newOwner, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping(value =  "/{id}/new-store")
    public ResponseEntity<Store> createStore(@PathVariable("id") long id, @RequestBody Store store){
        Store newStore = this.ownerService.createStore(id, store);
        return new ResponseEntity<>(newStore, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Owner> delete(@PathVariable("id") long id){
        boolean data = this.ownerService.deleteById(id);
        return (data)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
