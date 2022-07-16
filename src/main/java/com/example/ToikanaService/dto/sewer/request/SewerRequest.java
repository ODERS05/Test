package com.example.ToikanaService.dto.sewer.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerRequest{
    Long userId;

    Long doneAmount;

    Long orderId;

    String status;

    LocalDateTime ctsWhenDone;
}
