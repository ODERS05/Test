package com.example.ToikanaService.entity;

import com.example.ToikanaService.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity{
    @Column(name = "name_role", nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    Role role;
}
