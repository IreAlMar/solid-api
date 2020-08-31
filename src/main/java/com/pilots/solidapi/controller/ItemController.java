package com.pilots.solidapi.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.pilots.solidapi.repository.Item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    private static final String template = "Requested item %s.";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/getItem")
    public Item getItem(@RequestParam(value = "name", defaultValue = "nothing") String name) {
        return new Item( String.format(template, name), 8.3);
    }
}