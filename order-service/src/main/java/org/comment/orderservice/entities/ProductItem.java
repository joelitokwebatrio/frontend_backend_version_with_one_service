package org.comment.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.comment.orderservice.model.Product;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID Id;

    private UUID productId;
    private double price;
    private int quantity;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Orders orders;

    @Transient
    private Product product;
}
