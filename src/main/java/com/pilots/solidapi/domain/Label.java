package com.pilots.solidapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;
    private String itemDescription;

    protected Label() {
    }

    public Label(String itemName, double price, String itemDescription) {
        this.name = itemName;
        this.price = price;
        this.itemDescription = itemDescription;
    }

    @Override
    public String toString() {
        return String.format("Label[id=%d, name='%s', price=%.2f, description='%s']", id, name, price,
                itemDescription);
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

    public String getItemDescription() {
        return itemDescription;
    }
}
