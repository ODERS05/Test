package com.example.ToikanaService.dto.sewer.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerResponse {
    Long id;
    Long user_id;
    Long order_id;
    String status;
}
