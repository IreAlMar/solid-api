package com.pilots.solidapi.application;

import com.pilots.solidapi.domain.Item;

// port -> application
public interface ItemService {
    Item getItem(long id);

    Item getItem(String name);

    boolean saveItem(Item item);
}
