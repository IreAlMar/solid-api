package com.pilots.solidapi.infrastructure.internal.data.converter;

import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.ItemPrice;

import javax.persistence.AttributeConverter;

public class ItemPriceConverter implements AttributeConverter<ItemPrice, Double> {
    @Override
    public Double convertToDatabaseColumn(ItemPrice itemPrice) {
        if (itemPrice == null) {
            return null;
        }
        return itemPrice.getItemPrice();
    }

    @Override
    public ItemPrice convertToEntityAttribute(Double dbItemPrice) {
        try {
            return new ItemPrice(dbItemPrice);
        } catch (InvalidItemPriceException e) {
            return null;
        }
    }
}
