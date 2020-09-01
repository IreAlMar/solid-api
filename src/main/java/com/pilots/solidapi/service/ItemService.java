package com.pilots.solidapi.service;

import com.pilots.solidapi.repository.Item;
import com.pilots.solidapi.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItem(long id) {
        return itemRepository.findById(id);
    }

    public Item getItem(String name) {
        return itemRepository.findByName(name).get(0);
    }
}
