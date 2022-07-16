package com.example.ToikanaService.dto.sewer.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SewerUpdateRequest {
    Long id;

    Long doneAmount;

    String status;

    LocalDateTime ctsWhenDone;
}
