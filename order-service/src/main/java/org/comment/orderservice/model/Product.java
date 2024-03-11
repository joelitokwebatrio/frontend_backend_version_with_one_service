package org.comment.orderservice.model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Product {
    private UUID id;
    private String name;
    private double price;
    private int quantity;
}
