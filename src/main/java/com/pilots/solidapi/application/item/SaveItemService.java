package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;

// port -> application
public interface SaveItemService {
    boolean saveItem(Item item);

    Item saveItem(Double price) throws InvalidItemPriceException;
}
