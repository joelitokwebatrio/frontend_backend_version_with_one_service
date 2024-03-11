package org.comment.orderservice.restclients;

import org.comment.orderservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@FeignClient(url = "http://localhost:8087", name = "inventory-service")
public interface InventoryRestClient {

    @GetMapping("/api/products")
    List<Product> getAllProducts();

    @GetMapping("/api/products/{id}")
    Optional<Product> findProductById(@PathVariable UUID id);
}
