package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.UpdateItemPriceService;
import com.pilots.solidapi.domain.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemPriceServiceImpl implements UpdateItemPriceService {

    private final ItemRepository itemRepository;

    public UpdateItemPriceServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item updateItemPrice(long id, double price) {
        Item item = itemRepository.findById(id);
        if (null != item && price > 0) {
            item.setPrice(price);
            itemRepository.save(item);
            return itemRepository.findById(id);
        }
        //excepcion??
        
        return null;
    }
}
