package com.blackdev.bestCoffee.coffee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public Collection<Coffee> getAll(){
        return this.coffeeRepository.findAll();
    }

    public Optional<Coffee> getById(long id){
        return this.coffeeRepository.findById(id);
    }

    public Coffee create(Coffee coffee){
        return this.coffeeRepository.save(coffee);
    }

    public Coffee update(long id, Coffee coffee) {

        if(this.coffeeRepository.existsById(id)){
            this.coffeeRepository.saveAndFlush(coffee);
            return this.coffeeRepository.getOne(id);
        }

        return null;

    }

    public boolean deleteById(long id){
        boolean deleted = this.coffeeRepository.existsById(id);
        this.coffeeRepository.deleteById(id);
        return deleted;
    }
}
