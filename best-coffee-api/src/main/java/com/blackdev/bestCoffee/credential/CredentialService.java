package com.blackdev.bestCoffee.credential;

import com.blackdev.bestCoffee.owner.Owner;
import com.blackdev.bestCoffee.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    public void generateUserCredential(User user){
        Credential credential = new Credential(user);
        this.credentialRepository.save(credential);
    }

    public void generateOwnerCredential(Owner owner){
        Credential credential = new Credential(owner);
        this.credentialRepository.save(credential);
    }

    public Credential getByUsername(String username){
        return this.credentialRepository.findByUsername(username);
    }

    public void removeUserCredential(String username){
        this.credentialRepository.deleteByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Credential credential = credentialRepository.findByUsername(s);
        List authList = AuthorityUtils.createAuthorityList(credential.getRole().toString());

        return new org.springframework.security.core.userdetails.User(credential.getUsername(), credential.getPassword(), authList);
    }
}
