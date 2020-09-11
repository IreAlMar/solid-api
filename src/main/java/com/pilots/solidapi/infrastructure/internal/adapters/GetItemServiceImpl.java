package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.item.GetItemService;
import com.pilots.solidapi.domain.Item;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import org.springframework.stereotype.Service;

// adapter -> infrastructure
@Service
public class GetItemServiceImpl implements GetItemService {

    private final ItemRepository itemRepository;

    public GetItemServiceImpl(ItemRepository itemRepository) {
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

}
