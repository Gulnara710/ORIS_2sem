package ru.itis.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends OrisBaseEntity {

    private String description;
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}