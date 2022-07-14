package com.example.ToikanaService.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FloorEntity extends BaseEntity {
    @Column(name = "floor_name", nullable = false, unique = true)
    String floorName;

    @ManyToMany
    @JoinColumn(name = "user_id", nullable = false)
    List<UserEntity> userEntities;
}
