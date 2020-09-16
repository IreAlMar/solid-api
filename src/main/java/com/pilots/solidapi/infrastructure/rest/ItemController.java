package com.pilots.solidapi.infrastructure.rest;

import com.pilots.solidapi.application.item.GetItemService;
import com.pilots.solidapi.application.item.SaveItemService;
import com.pilots.solidapi.application.item.UpdateItemPriceService;
import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.domain.item.ItemName;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemIdException;
import com.pilots.solidapi.infrastructure.internal.exception.InvalidItemNameException;
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
    public ResponseEntity getItemByName(@RequestParam(value = "name", defaultValue = "empty") String name) {
        try {
            Item item = getItemService.getItem(new ItemName(name));
            return new ResponseEntity(item, HttpStatus.OK);
        } catch (InvalidItemNameException e) {
            return new ResponseEntity<>("Invalid name " + name, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getItem", params = "id")
    public ResponseEntity getItemById(@RequestParam(value = "id", defaultValue = "1") long id) {
        try {
            Item item = getItemService.getItem(id);
            return new ResponseEntity(item, HttpStatus.OK);
        } catch (InvalidItemIdException e) {
            return new ResponseEntity<>("Invalid id " + id, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/updateItemPrice", params = "id")
    public ResponseEntity<String> updateItemPrice(@RequestParam(value = "id", defaultValue = "1") long id,
                                                  @RequestParam(value = "price") double price) {
        Item item;
        try {
            item = updateItemPrice.updateItemPrice(id, price);
        } catch (InvalidItemPriceException e) {
            return new ResponseEntity<>("Invalid price " + price, HttpStatus.BAD_REQUEST);
        }
        if (item != null) {
            return new ResponseEntity(item, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(value = "/saveItem", params = "price")
    public ResponseEntity<String> saveItem(@RequestParam(value = "price") Double price) {
        try {
            Item savedItem = saveItemService.saveItem(price);
            return new ResponseEntity(savedItem, HttpStatus.CREATED);

        } catch (InvalidItemPriceException e) {
            return new ResponseEntity<>("Invalid price " + price, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public ResponseEntity<String> saveItemPost(@RequestBody Item item) {
        if (saveItemService.saveItem(item)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}