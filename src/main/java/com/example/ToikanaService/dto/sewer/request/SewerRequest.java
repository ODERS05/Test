package com.example.ToikanaService.dto.sewer.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerRequest{
    Long user_id;

    Long doneAmount;

    Long order_id;

    String status;
}
