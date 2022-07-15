package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.order.request.OrderRequest;
import com.example.ToikanaService.dto.order.response.OrderResponse;
import com.example.ToikanaService.entity.OrderEntity;
import com.example.ToikanaService.mapper.OrderMapper;
import com.example.ToikanaService.repository.OrderRepository;
import com.example.ToikanaService.service.OrderService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    final OrderRepository orderRepository;
    @Override
    public OrderResponse save(OrderRequest t) {
        OrderEntity order = orderRepository.save(OrderEntity.builder()
                .clothesType(t.getClothType())
                .amount(t.getAmount())
                .unitPrice(t.getUnitPrice())
                .build());

        return OrderMapper.INSTANCE.toOrderResponse(order);
    }

    @Override
    public List<OrderResponse> getAll() {
        return OrderMapper.INSTANCE.toOrdersResponse(orderRepository.findAll());
    }

    @Override
    public OrderResponse findById(Long id) {
        return OrderMapper.INSTANCE.toOrderResponse(orderRepository.getById(id));
    }
}
