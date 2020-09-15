package com.pilots.solidapi.infrastructure.rest;

import com.pilots.solidapi.application.item.GetItemService;
import com.pilots.solidapi.application.item.SaveItemService;
import com.pilots.solidapi.application.item.UpdateItemPriceService;
import com.pilots.solidapi.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    // depends on the port, not on the adapter
    @Autowired
    private GetItemService getItemService;

    @Autowired
    private SaveItemService saveItemService;

    @Autowired
    private UpdateItemPriceService updateItemPrice;

    @GetMapping(value = "/getItem", params = "name")
    public Item getItemByName(@RequestParam(value = "name", defaultValue = "empty") String name) {
        return getItemService.getItem(name);
    }

    @GetMapping(value = "/getItem", params = "id")
    public Item getItemById(@RequestParam(value = "id", defaultValue = "1") long id) {
        return getItemService.getItem(id);
    }

    @GetMapping(value = "/updateItemPrice", params = "id")
    public ResponseEntity<Item> updateItemPrice(@RequestParam(value = "id", defaultValue = "1") long id,
                                                @RequestParam(value = "price") double price) {
        Item item = updateItemPrice.updateItemPrice(id, price);
        if (item != null) {
            return new ResponseEntity(item, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public ResponseEntity<String> saveItem(@RequestBody Item item) {
        if (saveItemService.saveItem(item)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}