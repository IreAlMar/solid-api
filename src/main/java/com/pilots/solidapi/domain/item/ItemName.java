package com.pilots.solidapi.domain.item;

import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;

public class ItemName {
    String itemName;

    public ItemName(String itemName) throws InvalidItemNameException {
        validate(itemName);
        this.itemName = itemName;
    }

    private void validate(String itemName) throws InvalidItemNameException {
        if (itemName == null || itemName.equals("") || itemName.isEmpty()) {
            throw new InvalidItemNameException();
        }

    }

    public String getItemName() {
        return itemName;
    }
}
