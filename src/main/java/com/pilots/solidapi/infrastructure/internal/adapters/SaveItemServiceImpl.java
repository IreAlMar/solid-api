package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.SaveItemService;
import com.pilots.solidapi.domain.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// adapter -> infrastructure
@Service
public class SaveItemServiceImpl implements SaveItemService {

    private final ItemRepository itemRepository;
    private static final Logger log = LoggerFactory.getLogger(SaveItemServiceImpl.class);

    public SaveItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
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
