package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.NameRequestService;
import com.pilots.solidapi.application.item.SaveItemService;
import com.pilots.solidapi.domain.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// adapter -> infrastructure
@Service
public class SaveItemServiceImpl implements SaveItemService {

    private final ItemRepository itemRepository;
    private final NameRequestService nameRequestService;
    private static final Logger log = LoggerFactory.getLogger(SaveItemServiceImpl.class);

    public SaveItemServiceImpl(ItemRepository itemRepository, NameRequestService nameRequestService) {
        this.itemRepository = itemRepository;
        this.nameRequestService = nameRequestService;
    }

    @Override
    public boolean saveItem(Item item) {
        String itemName = nameRequestService.getName();
        Item itemWithRandomName = new Item(itemName, item.getPrice());

        if (isValidItem(itemWithRandomName)) {
            log.info("Repo saved item: " + itemRepository.save(itemWithRandomName));
            return true;
        }
        log.info("Invalid item " + itemWithRandomName.toString());
        return false;
    }

    private boolean isValidItem(Item item) {
        return (item.getName() != "") && (item.getPrice() > 0);
    }
}
