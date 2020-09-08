package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.ItemService;
import com.pilots.solidapi.domain.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// adapter -> infrastructure
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItem(long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Item getItem(String name) {
        return itemRepository.findByName(name).get(0);
    }

    @Override
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

    private boolean isValidItem(Item item) {
        return (item.getName() != "") && (item.getPrice() > 0);
    }
}
