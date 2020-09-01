package com.pilots.solidapi.controller;

import com.pilots.solidapi.repository.Item;

import com.pilots.solidapi.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}