package com.example.ToikanaService.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    UserEntity userEntity;


    @Column(name = "header",nullable = false)
    String header;


}
