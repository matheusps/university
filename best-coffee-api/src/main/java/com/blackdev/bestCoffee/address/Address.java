package com.blackdev.bestCoffee.address;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String addressLine;
    private String city;
    private String country;
    private String zip;
    private String state;
    private String complement;

}
