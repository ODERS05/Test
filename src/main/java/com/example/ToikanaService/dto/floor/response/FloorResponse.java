package com.example.ToikanaService.dto.floor.response;

import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.entity.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FloorResponse {
    Long id;

    List<SewerResponse> sewers;

    String clothType;

    Long amount;

    Integer unitPrice;

    String floorName;
}
