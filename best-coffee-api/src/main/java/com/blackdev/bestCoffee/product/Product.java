package com.blackdev.bestCoffee.product;

import com.blackdev.bestCoffee.coffee.Coffee;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Coffee coffee;

    @NotNull(message = "A product must have a price")
    private double price;

    @NotNull(message = "A product must have a stock")
    private int stock;

    public Product() {
    }

    public Product(Coffee coffee, @NotNull(message = "A product must have a price") double price, @NotNull(message = "A product must have a stock") int stock) {
        this.coffee = coffee;
        this.price = price;
        this.stock = stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                stock == product.stock &&
                Objects.equals(coffee, product.coffee);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, coffee, price, stock);
    }
}
