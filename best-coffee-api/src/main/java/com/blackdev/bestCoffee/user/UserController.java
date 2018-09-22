package com.blackdev.bestCoffee.user;

import com.blackdev.bestCoffee.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole()")
    @GetMapping()
    public ResponseEntity<Collection<User>> getUsers(){
        Collection<User> data = this.userService.getUsers();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/id/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable("id") Long id){
        Optional<User> data = this.userService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/email/{email}")
    public ResponseEntity<Optional<User>> getByEmail(@PathVariable("email") String email){
        Optional<User> data = this.userService.getByEmail(email);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole()")
    @GetMapping(value = "/username/{username}")
    public ResponseEntity<Optional<User>> getByUsername(@PathVariable("username") String username){
        Optional<User> data = this.userService.getByUsername(username);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping(value =  "/register")
    public ResponseEntity<User> create(@RequestBody User user){
        User newUser = this.userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping(value =  "/{id}/newAddress")
    public ResponseEntity<Address> newAddress(@PathVariable("id") Long id, @RequestBody Address address){
        Address newAddress = this.userService.addAddress(id, address);
        return new ResponseEntity<>(newAddress, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") long id){
        boolean data = this.userService.deleteById(id);
        return (data)?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
