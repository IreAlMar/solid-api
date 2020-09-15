package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.Item;

// port -> application
public interface GetItemService {
    Item getItem(long id);

    Item getItem(String name);
}