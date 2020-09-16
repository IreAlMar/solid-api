package com.pilots.solidapi.infrastructure.internal.data.converter;

import com.pilots.solidapi.domain.item.ItemName;

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
        if (dbItemName == null || dbItemName.isEmpty()) {
            return null;
        }

        return new ItemName(dbItemName);
    }
}
