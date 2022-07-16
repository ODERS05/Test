package com.example.ToikanaService.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity{
    @Column(name = "clothes_type", nullable = false)
    String clothesType;

    @Column(name = "amount", nullable = false)
    Long amount;

    @Column(name = "unit_price", nullable = false)
    Integer unitPrice;

}
