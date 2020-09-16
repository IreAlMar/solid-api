package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.UpdateItemPriceService;
import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemPriceServiceImpl implements UpdateItemPriceService {

    private final ItemRepository itemRepository;

    public UpdateItemPriceServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item updateItemPrice(long id, double price) throws InvalidItemPriceException {
        Item item = itemRepository.findById(id);
        if (null != item) {
            item.setPrice(price);
            return itemRepository.save(item);
        }

        return null;
    }
}
