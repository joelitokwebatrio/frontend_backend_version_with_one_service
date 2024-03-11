package org.comment.orderservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.comment.orderservice.enums.OrderState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private OrderState orderState;
    @OneToMany(mappedBy = "orders")
    List<ProductItem> productItems= new ArrayList<>();
}
