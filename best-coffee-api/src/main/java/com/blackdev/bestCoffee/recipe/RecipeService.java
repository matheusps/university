package com.blackdev.bestCoffee.recipe;

import com.blackdev.bestCoffee.user.User;
import com.blackdev.bestCoffee.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    public Collection<Recipe> getAll(){
        return this.recipeRepository.findAll();
    }

    public Optional<Recipe> getById(long id){
        return this.recipeRepository.findById(id);
    }

    public Recipe create(Recipe recipe, long userId){

        User user = this.userRepository.getOne(userId);

        recipe.setCreatedBy(user);

        return this.recipeRepository.save(recipe);
    }

    public boolean deleteById(long id){
        boolean deleted = this.recipeRepository.existsById(id);
        this.recipeRepository.deleteById(id);
        return deleted;
    }

}
