package com.pilots.solidapi;

import com.pilots.solidapi.repository.Item;
import com.pilots.solidapi.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SolidApiApplication {

    private static final Logger log = LoggerFactory.getLogger(SolidApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SolidApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ItemRepository repository) {
        return (args->{
            //save a few items
            repository.save(new Item("Green plant pot", 1.50));
            repository.save(new Item("Aloe vera", 4.20));
            repository.save(new Item("Rosemary seeds", 0.75));
            repository.save(new Item("Lavender plant", 2.85));
            repository.save(new Item("Lemongrass seeds", 3.0));

            //fetch all items
            log.info("Items found with findAll():");
            log.info("---------------------------");
            for (Item item : repository.findAll()) {
                log.info(item.toString());
            }
            log.info("");

            //fetch an individual item by ID
            Item item = repository.findById(1L);
            log.info("Item found with findById(1L):");
            log.info("-----------------------------");
            log.info(item.toString());
            log.info("");

            //fetch items by name
            log.info("Item found with findByName('Lavender plant')");
            log.info("-----------------------------");
            repository.findByName("Lavender plant").forEach(i->{
                log.info(i.toString());
            });
            log.info("");
        });
    }

}
