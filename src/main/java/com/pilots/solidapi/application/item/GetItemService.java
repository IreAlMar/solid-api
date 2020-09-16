package com.pilots.solidapi.application.item;

import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.domain.item.ItemName;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemIdException;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;

// port -> application
public interface GetItemService {
    Item getItem(long id)throws InvalidItemIdException;

    Item getItem(ItemName name) throws InvalidItemNameException;
}
