package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.order.response.OrderResponse;
import com.example.ToikanaService.dto.sewer.request.SewerRequest;
import com.example.ToikanaService.dto.sewer.request.SewerUpdateRequest;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.entity.OrderEntity;
import com.example.ToikanaService.entity.SewerEntity;
import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.exception.OrderNotFoundException;
import com.example.ToikanaService.exception.UserNotFoundException;
import com.example.ToikanaService.mapper.SewerMapper;
import com.example.ToikanaService.repository.OrderRepository;
import com.example.ToikanaService.repository.SewerRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.service.SewerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SewerServiceImpl implements SewerService {
    final SewerRepository sewerRepository;
    final OrderRepository orderRepository;
    final UserRepository userRepository;

    @Override
    public SewerResponse save(SewerRequest t) {
        OrderEntity order = orderRepository.findById(t.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Такого заказа нет", HttpStatus.BAD_REQUEST));
        UserEntity user = userRepository.findById(t.getUserId()).orElseThrow(() -> new UserNotFoundException("Такого пользователя нет", HttpStatus.BAD_REQUEST));
        SewerEntity sewer = sewerRepository.save(SewerEntity.builder()
                .status(t.getStatus())
                .order(order)
                .doneAmount(t.getDoneAmount())
                .user(user)
                .ctsWhenDone(LocalDateTime.now())
                .build());
        OrderResponse orderResponse = OrderResponse.builder()
                .amount(order.getAmount())
                .clothType(order.getClothesType())
                .unitPrice(order.getUnitPrice()).build();
        UserResponse userResponse = UserResponse.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
        return SewerResponse.builder()
                .id(sewer.getId())
                .status(t.getStatus())
                .doneAmount(t.getDoneAmount())
                .login(userResponse.getLogin())
                .clothType(orderResponse.getClothType())
                .amount(orderResponse.getAmount())
                .unitPrice(orderResponse.getUnitPrice())
                .ctsWhenDone(LocalDateTime.now())
                .email(userResponse.getEmail())
                .build();
    }

    @Override
    public List<SewerResponse> getAll() {

        List<SewerEntity> sewerEntities = sewerRepository.findAll();
        List<SewerResponse> sewerResponses = new ArrayList<>();
        for (SewerEntity sewerEntity : sewerEntities) {
            sewerResponses.add(SewerResponse.builder()
                    .status(sewerEntity.getStatus())
                    .ctsWhenDone(sewerEntity.getCtsWhenDone())
                    .doneAmount(sewerEntity.getDoneAmount())
                    .clothType(sewerEntity.getOrder().getClothesType())
                    .login(sewerEntity.getUser().getLogin())
                    .unitPrice(sewerEntity.getOrder().getUnitPrice())
                    .id(sewerEntity.getId())
                    .amount(sewerEntity.getOrder().getAmount())
                    .email(sewerEntity.getUser().getEmail())
                    .build());
        }

        return sewerResponses;
    }

    @Override
    public SewerResponse findById(Long id) {
        return SewerMapper.INSTANCE.toSewerResponse(sewerRepository.getById(id));

    }

    @Override
    public Boolean updateSewer(SewerUpdateRequest t) {
        SewerEntity sewer = sewerRepository.getById(t.getId());
        sewer.setDoneAmount(t.getDoneAmount());
        sewer.setStatus(t.getStatus());
        sewer.setCtsWhenDone(t.getCtsWhenDone());
        sewerRepository.save(sewer);
        return sewer.getId() != null;
    }

    @Override
    public BigDecimal countSewerSalary(SewerRequest t){
        SewerEntity sewerEntity;
        return null;
    }
}
