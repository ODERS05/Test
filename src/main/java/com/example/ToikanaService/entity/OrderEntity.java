package com.example.ToikanaService.entity;

import com.example.ToikanaService.enums.ClothesType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity extends BaseEntity{

    @Column(name = "clothes_type", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    ClothesType clothesType;

    @Column(name = "amount", nullable = false, unique = true)
    Long amount;

    @Column(name = "unit_price", nullable = false, unique = true)
    Integer unitPrice;
}
