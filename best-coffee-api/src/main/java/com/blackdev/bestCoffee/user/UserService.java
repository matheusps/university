package com.blackdev.bestCoffee.user;

import com.blackdev.bestCoffee.address.Address;
import com.blackdev.bestCoffee.address.AddressRepository;
import com.blackdev.bestCoffee.credential.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private AddressRepository addressRepository;

    public Collection<User> getUsers(){
        return this.repository.findAll();
    }

    public Optional<User> getById(long id){
        return this.repository.findById(id);
    }

    public Optional<User> getByEmail(String email){
        return this.repository.findUserByEmail(email);
    }

    public Optional<User> getByUsername(String username){
        return this.repository.findUserByUsername(username);
    }

    public User create(User user){
        User newUser = this.repository.save(user);

        if(newUser != null){
            this.credentialService.generateUserCredential(newUser);
        }

        return newUser;
    }

    public Address addAddress(long id, Address address){
        Address newAddress = this.addressRepository.save(address);
        if(newAddress != null){
            Optional<User> user = this.getById(id);
            if(user.isPresent()){
                user.get().getAddresses().add(address);
                this.repository.saveAndFlush(user.get());
            }
        }
        return newAddress;
    }

    public boolean deleteById(long id){
        User res = repository.getOne(id);
        this.repository.deleteById(id);
        return (res != null);
    }
}
