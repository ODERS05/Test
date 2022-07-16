package com.example.ToikanaService.service;

import com.example.ToikanaService.dto.sewer.request.SewerRequest;
import com.example.ToikanaService.dto.sewer.request.SewerUpdateRequest;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;

import java.math.BigDecimal;

public interface SewerService extends BaseService<SewerResponse, SewerRequest> {
    Boolean updateSewer(SewerUpdateRequest t);
    BigDecimal countSewerSalary();
}
