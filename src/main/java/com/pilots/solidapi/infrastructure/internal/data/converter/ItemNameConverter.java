package com.pilots.solidapi.infrastructure.internal.data.converter;

import com.pilots.solidapi.domain.item.ItemName;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ItemNameConverter implements AttributeConverter<ItemName, String> {
    @Override
    public String convertToDatabaseColumn(ItemName itemName) {
        if (itemName == null) {
            return null;
        }
        return itemName.getItemName();
    }

    @Override
    public ItemName convertToEntityAttribute(String dbItemName) {
        ItemName itemName = null;
        if (dbItemName == null || dbItemName.isEmpty()) {
            return null;
        }

        try {
            itemName = new ItemName(dbItemName);
        } catch (InvalidItemNameException e) {
            e.printStackTrace();
        }

        return itemName;
    }
}
