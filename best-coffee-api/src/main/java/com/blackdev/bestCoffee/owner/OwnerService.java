package com.blackdev.bestCoffee.owner;

import com.blackdev.bestCoffee.credential.CredentialService;
import com.blackdev.bestCoffee.store.Store;
import com.blackdev.bestCoffee.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private CredentialService credentialService;

    public Collection<Owner> getOwners(){
        return this.ownerRepository.findAll();
    }

    public Optional<Owner> getById(long id){
        return this.ownerRepository.findById(id);
    }

    public Owner getOwnerByUsername(String username){
        return this.ownerRepository.getOwnerByUsername(username);
    }

    public Owner create(Owner owner){
        Owner newOwner = this.ownerRepository.save(owner);

        if(newOwner != null){
            this.credentialService.generateOwnerCredential(owner);
        }

        return newOwner;
    }

    public Store createStore(long id, Store store){
        Optional<Owner> owner = this.getById(id);
        if(owner.isPresent()){
            Store newStore = this.storeService.create(store);
            if(newStore != null) {
                owner.get().getStores().add(store);
                this.ownerRepository.saveAndFlush(owner.get());
                return newStore;
            }
            return null;
        }
        return null;
    }

    public boolean deleteById(long id){
        boolean res = this.ownerRepository.existsById(id);
        this.ownerRepository.deleteById(id);
        return res;
    }

}
