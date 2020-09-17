package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.NameRequestService;
import com.pilots.solidapi.application.item.SaveItemService;
import com.pilots.solidapi.domain.item.*;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;
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
    public Item saveItem(String name, Double price) throws InvalidItemPriceException, InvalidItemNameException {
        ItemName itemName = new ItemName(name);
        ItemPrice itemPrice = new ItemPrice(price);
        Item validItem = new Item(itemName, itemPrice);
        Item savedItem = itemRepository.save(validItem);

        return savedItem;
        //        aqui iria lo de crear un evento?
    }

    @Override
    public Item saveItemRandomName(Double price) throws InvalidItemPriceException, InvalidItemNameException {
        ItemName itemName = new ItemName(nameRequestService.getName());
        Item item = new Item(itemName, new ItemPrice(price));
        Item savedItem = itemRepository.save(item);

        return savedItem;
    }

}
