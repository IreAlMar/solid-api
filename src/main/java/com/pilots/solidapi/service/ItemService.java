package com.pilots.solidapi.service;

import com.pilots.solidapi.repository.Item;
import com.pilots.solidapi.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItem(long id) {
        return itemRepository.findById(id);
    }

    public Item getItem(String name) {
        return itemRepository.findByName(name).get(0);
    }

    public boolean saveItem(Item item) {
        if (isValidItem(item)) {
            itemRepository.save(item);
            itemRepository.findByName(item.getName()).forEach(i->{
                log.info(i.toString());
            });
            return true;
        }
        return false;
    }

    public boolean isValidItem(Item item) {
        return (item.getName() != "") && (item.getPrice() > 0);
    }
}
