package com.pilots.solidapi.infrastructure.internal.data;

import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.domain.item.ItemName;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// infrastructure layer because it is coupled with Spring -> external -> not considered port as it is coupled with
// the DB
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByName(ItemName name);

    Item findById(long id);
}
