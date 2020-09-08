package com.pilots.solidapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;

    //Constructor for the shake of JPA
    protected Item(){}

    public Item(String description, double price) {

        this.name = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, name='%s', price=%.2f]", id, name, price);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
