package com.pilots.solidapi.domain.item;

public class ItemPrice {
    private final double MAX_PRICE = 1000;
    private final double MIN_PRICE = 0;

    Double price;

    public ItemPrice(double price) throws InvalidItemPriceException {
        if (isValidPrice(price)) {
            this.price = price;
        }
        else {
            throw new InvalidItemPriceException();
        }
    }

    private boolean isValidPrice(Double price) {
        return price != null && price > MIN_PRICE && price < MAX_PRICE;
    }

    public Double getItemPrice() {
        return price;
    }
}
