package com.example.ToikanaService.mapper;

import com.example.ToikanaService.dto.order.response.OrderResponse;
import com.example.ToikanaService.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderResponse toOrderResponse(OrderEntity order);
    List<OrderResponse> toOrdersResponse(List<OrderEntity> orderEntities);
}
