package com.blackdev.bestCoffee.rating;

import com.blackdev.bestCoffee.coffee.Coffee;
import com.blackdev.bestCoffee.coffee.CoffeeRepository;
import com.blackdev.bestCoffee.user.User;
import com.blackdev.bestCoffee.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Collection<Rating> getAll(){
        return this.ratingRepository.findAll();
    }

    public Optional<Rating> getById(long id){
        return this.ratingRepository.findById(id);
    }

    public Rating create(Rating rating, long userId, long coffeeId){

        User user = this.userRepository.getOne(userId);
        Coffee coffee = this.coffeeRepository.getOne(coffeeId);

        rating.setUser(user);
        rating.setCoffee(coffee);

        return this.ratingRepository.save(rating);
    }

    public boolean deleteById(long id){
        boolean deleted = this.ratingRepository.existsById(id);
        this.ratingRepository.deleteById(id);
        return deleted;
    }
}
