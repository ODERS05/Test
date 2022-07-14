package com.example.ToikanaService.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sewer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity executorSewer;

    @Column(name = "amount", nullable = false, unique = true)
    Long doneAmount;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    OrderEntity order;

    @Column(name = "status", nullable = false, unique = true)
    String status;

    //Дата обновления статуса с NEw на DONE
    @Column(name = "cts", nullable = false, unique = true)
    LocalDateTime ctsWhenDone;
}
