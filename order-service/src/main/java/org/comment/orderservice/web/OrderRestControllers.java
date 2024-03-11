package org.comment.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.comment.orderservice.entities.Orders;
import org.comment.orderservice.entities.ProductItem;
import org.comment.orderservice.model.Product;
import org.comment.orderservice.repositories.OrderRepository;
import org.comment.orderservice.restclients.InventoryRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderRestControllers {
    private final OrderRepository orderRepository;
    private final InventoryRestClient inventoryRestClient;

    @GetMapping("/orders")
    public List<Orders> findAllOrders() {
        List<Orders> allOrders = orderRepository.findAll();
        allOrders.stream().map(Orders::getProductItems).flatMap(Collection::stream).forEach(this::setProduct);
        return allOrders;
    }

    @GetMapping("/orders/{id}")
    public Orders findOrderById(@PathVariable UUID id) {
        Optional<Orders> orderOptional = orderRepository.findById(id);
        orderOptional.map(Orders::getProductItems)
                .stream().flatMap(Collection::stream)
                .forEach(productItem -> productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId())
                        .orElse(getProduct())));

        return orderOptional.orElse(null);
    }

    private Product getProduct() {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Not found Product");
        product.setPrice(0.00);
        product.setQuantity(0);
        return product;
    }

    private void setProduct(ProductItem productItem) {
        productItem.setProduct(inventoryRestClient.findProductById(productItem.getProductId()).orElse(null));
    }
}
