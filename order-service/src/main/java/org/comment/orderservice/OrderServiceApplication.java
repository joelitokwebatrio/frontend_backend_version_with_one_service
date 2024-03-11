package org.comment.orderservice;

import org.comment.orderservice.entities.Orders;
import org.comment.orderservice.entities.ProductItem;
import org.comment.orderservice.enums.OrderState;
import org.comment.orderservice.repositories.OrderRepository;
import org.comment.orderservice.repositories.ProductItemRepository;
import org.comment.orderservice.restclients.InventoryRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(OrderRepository orderRepository,
                                        ProductItemRepository productItemRepository,
                                        InventoryRestClient inventoryRestClient) {
        return  args -> {
            //List<Product> allProducts = inventoryRestClient.getAllProducts();
            List<UUID> productsIds = List.of(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID());
            for (int i = 0; i < 5; i++) {
                Orders order = Orders.builder()
                        .date(LocalDate.now())
                        .orderState(OrderState.PENDING)
                        .build();
                Orders savedOrder = orderRepository.save(order);
                productsIds.forEach(pId->{
                    ProductItem productItem = ProductItem.builder()
                            .productId(pId)
                            .quantity(new Random().nextInt(20))
                            .price(Math.random()*6000+100)
                            .orders(savedOrder)
                            .build();
                    productItemRepository.save(productItem);
                });

            }
        };
    }
}
