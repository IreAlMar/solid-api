package com.pilots.solidapi.controller;

import com.pilots.solidapi.repository.Item;

import com.pilots.solidapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value = "/getItem", params = "name")
    public Item getItemByName(@RequestParam(value = "name", defaultValue = "empty") String name) {
        return itemService.getItem(name);
    }

    @GetMapping(value = "/getItem", params = "id")
    public Item getItemById(@RequestParam(value = "id", defaultValue = "1") long id) {
        System.out.println("Testing");
        return itemService.getItem(id);
    }

    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public ResponseEntity< String > saveItem(@RequestBody Item item){
        if (itemService.saveItem(item)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}