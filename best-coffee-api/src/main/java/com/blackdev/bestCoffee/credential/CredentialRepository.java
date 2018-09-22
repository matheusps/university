package com.blackdev.bestCoffee.credential;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long> {
    void deleteByUsername(String username);
    Credential findByUsername(String username);
}
