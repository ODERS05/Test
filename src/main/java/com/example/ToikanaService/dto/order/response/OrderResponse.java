package com.example.ToikanaService.dto.order.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;

    String clothType;

    Long amount;

    Integer unitPrice;
}
