package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.NameRequestService;
import com.pilots.solidapi.application.item.SaveItemService;
import com.pilots.solidapi.domain.item.*;
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
        ItemName itemName = new ItemName(nameRequestService.getName());
        Item itemWithRandomName = new Item(itemName, item.getPrice());

        log.info("Repo saved item: " + itemRepository.save(itemWithRandomName));
        return true;
        //        aqui iria lo de crear un evento?
        //        log.info("Invalid item " + itemWithRandomName.toString());
        //        return false;
    }

    @Override
    public Item saveItem(Double price) throws InvalidItemPriceException {
        ItemName itemName = new ItemName(nameRequestService.getName());
        Item item = new Item(itemName, new ItemPrice(price));
        Item savedItem = itemRepository.save(item);

        log.info("Repo saved item: " + savedItem.toString());

        return savedItem;
    }

}
