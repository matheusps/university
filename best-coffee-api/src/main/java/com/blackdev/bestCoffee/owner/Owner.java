package com.blackdev.bestCoffee.owner;

import com.blackdev.bestCoffee.store.Store;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "A owner must have a name")
    private String name;

    @NotNull(message = "A owner must have a username")
    @Column(unique = true)
    private String username;

    @NotNull(message = "A owner must have a email")
    @Email
    @Column(unique = true)
    private String email;

    @NotNull(message = "A owner must have a password")
    private String password;

    @OneToMany(targetEntity = Store.class, cascade = CascadeType.ALL)
    private Collection<Store> stores;

    public Owner() {
        this.stores = new ArrayList<>();
    }

    public Owner(@NotNull(message = "A owner must have a name") String name, @NotNull(message = "A owner must have a username") String username, @NotNull(message = "A owner must have a email") @Email String email, @NotNull(message = "A owner must have a password") String password, Collection<Store> stores) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.stores = stores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Store> getStores() {
        return stores;
    }

    public void setStores(Collection<Store> stores) {
        this.stores = stores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
                Objects.equals(name, owner.name) &&
                Objects.equals(username, owner.username) &&
                Objects.equals(email, owner.email) &&
                Objects.equals(password, owner.password) &&
                Objects.equals(stores, owner.stores);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, username, email, password, stores);
    }
}
