package com.pilots.solidapi.domain.item;

import java.util.Objects;

public class ItemName {
    String itemName;

    public ItemName(String itemName) {
        if (isValidName(itemName)) {
            this.itemName = itemName;
        }
    }

    private boolean isValidName(String itemName) {
        return !Objects.requireNonNull(itemName).isEmpty();
    }

    public String getItemName() {
        return itemName;
    }
}
