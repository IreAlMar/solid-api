package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.Item;

public interface UpdateItemPriceService {
    Item updateItemPrice(long id, double price);
}
