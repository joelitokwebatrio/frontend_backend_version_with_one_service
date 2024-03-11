package org.comment.orderservice.repositories;

import org.comment.orderservice.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
}
