package com.blackdev.bestCoffee.store;

import com.blackdev.bestCoffee.product.Product;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "A store must have a name")
    private String name;

    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    private Collection<Product> products;

    public Store() {
        this.products = new ArrayList<>();
    }

    public Store(@NotNull(message = "A store must have a name") String name, Collection<Product> products) {
        this.name = name;
        this.products = products;
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

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return id == store.id &&
                Objects.equals(name, store.name) &&
                Objects.equals(products, store.products);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, products);
    }
}
