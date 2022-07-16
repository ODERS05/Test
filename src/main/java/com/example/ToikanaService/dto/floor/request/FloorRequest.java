package com.example.ToikanaService.dto.floor.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FloorRequest {
    List<Long> sewerId;
    Long orderId;
    String floorName;
}
