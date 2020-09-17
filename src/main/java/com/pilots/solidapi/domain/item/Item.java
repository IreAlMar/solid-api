package com.pilots.solidapi.domain.item;

import com.pilots.solidapi.infrastructure.internal.data.converter.ItemPriceConverter;
import com.pilots.solidapi.infrastructure.internal.data.converter.ItemNameConverter;

import javax.persistence.*;

//@Entity creates a table of name Item
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Convert(converter = ItemNameConverter.class)
    private ItemName name;
    @Convert(converter = ItemPriceConverter.class)
    private ItemPrice price;

    //Constructor for the shake of JPA
    protected Item(){}

    public Item(ItemName name, ItemPrice price) {
        //validation??

        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item[id=%d, name='%s', price=%.2f]", id, name.getItemName(), price.getItemPrice());
    }

    public long getId() {
        return id;
    }

    public ItemName getName() {
        return name;
    }

    public ItemPrice getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidItemPriceException {
        this.price = new ItemPrice(price);
    }
}
