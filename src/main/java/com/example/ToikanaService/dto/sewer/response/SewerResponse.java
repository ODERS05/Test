package com.example.ToikanaService.dto.sewer.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerResponse {
    Long id;

    String login;

    Long doneAmount;

    String clothType;

    Long amount;

    Integer unitPrice;

    String status;

    LocalDateTime ctsWhenDone;
}
