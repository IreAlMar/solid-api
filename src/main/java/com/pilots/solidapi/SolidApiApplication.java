package com.pilots.solidapi;

import com.pilots.solidapi.domain.Label;
import com.pilots.solidapi.domain.item.InvalidItemPriceException;
import com.pilots.solidapi.domain.item.Item;
import com.pilots.solidapi.domain.item.ItemName;
import com.pilots.solidapi.domain.item.ItemPrice;
import com.pilots.solidapi.infrastructure.internal.data.ItemRepository;
import com.pilots.solidapi.infrastructure.internal.data.LabelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SolidApiApplication {

    private static final Logger log = LoggerFactory.getLogger(SolidApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SolidApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner itemData(ItemRepository repository) {
        return (args->{
            //save a few items
            try {
                repository.save(new Item(new ItemName("Pot"), new ItemPrice(1.50)));
                repository.save(new Item(new ItemName("Aloe vera"), new ItemPrice(4.20)));
                repository.save(new Item(new ItemName("Rosemary seeds"), new ItemPrice(0.75)));
                repository.save(new Item(new ItemName("Lavender plant"), new ItemPrice(2.85)));
                repository.save(new Item(new ItemName("Lemongrass seeds"), new ItemPrice(3.0)));
            } catch (InvalidItemPriceException e) {
                e.printStackTrace();
            }

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
            repository.findByName(new ItemName("Lavender plant")).forEach(i->{
                log.info(i.toString());
            });
            log.info("");
        });
    }

    @Bean
    public CommandLineRunner labelData(LabelRepository repository) {
        return (args->{
            //save a few items
            repository.save(new Label("Pot", 1.50, "Plant pot"));
            repository.save(new Label("Aloe vera", 4.20, "Aloe vera plant with pot"));
            repository.save(new Label("Rosemary seeds", 0.75, "Packet of 50 rosemary seeds"));
            repository.save(new Label("Lavender plant", 2.85, "Young lavender plant pot"));

            //fetch all items
            log.info("Labels found with findAll():");
            log.info("---------------------------");
            for (Label label : repository.findAll()) {
                log.info(label.toString());
            }
            log.info("");

            //fetch an individual labels by ID
            Label label = repository.findById(9L);
            log.info("Label found with findById(9L):");
            log.info("-----------------------------");
            log.info(label.toString());
            log.info("");

            //fetch labels by item name
            log.info("Label found with findByName('Lavender plant')");
            log.info("-----------------------------");
            repository.findByName("Lavender plant").forEach(i->{
                log.info(i.toString());
            });
            log.info("");
        });
    }

}
