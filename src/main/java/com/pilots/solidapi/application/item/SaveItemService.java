package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.Item;

// port -> application
public interface SaveItemService {
    boolean saveItem(Item item);
}
