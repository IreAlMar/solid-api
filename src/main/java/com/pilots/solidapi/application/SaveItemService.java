package com.pilots.solidapi.application;

import com.pilots.solidapi.domain.Item;

// port -> application
public interface SaveItemService {
    boolean saveItem(Item item);
}
