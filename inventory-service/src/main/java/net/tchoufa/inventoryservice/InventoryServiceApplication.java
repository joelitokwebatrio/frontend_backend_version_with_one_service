package net.tchoufa.inventoryservice;

import net.tchoufa.inventoryservice.entities.Product;
import net.tchoufa.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("orange").price(123).quantity(15).build());
            productRepository.save(Product.builder().name("coca").price(123).quantity(15).build());
            productRepository.save(Product.builder().name("banana").price(123).quantity(15).build());
        };
    }
}
