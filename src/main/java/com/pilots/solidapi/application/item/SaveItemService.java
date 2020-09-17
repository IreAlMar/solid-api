package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;

// port -> application
public interface SaveItemService {
    Item saveItem(String name, Double price) throws InvalidItemPriceException, InvalidItemNameException;

    Item saveItemRandomName(Double price) throws InvalidItemPriceException, InvalidItemNameException;
}
