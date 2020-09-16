package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;

public interface UpdateItemPriceService {
    Item updateItemPrice(long id, double price) throws InvalidItemPriceException;
}
