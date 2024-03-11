package org.comment.orderservice.repositories;

import org.comment.orderservice.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductItemRepository  extends JpaRepository<ProductItem, UUID> {
}
