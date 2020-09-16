package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.GetItemService;
import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.domain.item.ItemName;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemIdException;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;
import org.springframework.stereotype.Service;

import java.util.List;

// adapter -> infrastructure
@Service
public class GetItemServiceImpl implements GetItemService {

    private final ItemRepository itemRepository;

    public GetItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItem(long id) throws InvalidItemIdException {
        Item item = itemRepository.findById(id);

        if (item == null) {
            throw new InvalidItemIdException();
        }

        return item;
    }

    @Override
    public Item getItem(ItemName name) throws InvalidItemNameException {
        List<Item> itemList = itemRepository.findByName(name);

        if (itemList.isEmpty()) {
            throw new InvalidItemNameException();
        }
        return itemList.get(0);
    }

}
