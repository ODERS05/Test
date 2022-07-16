package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.entity.FloorEntity;
import com.example.ToikanaService.entity.OrderEntity;
import com.example.ToikanaService.entity.SewerEntity;
import com.example.ToikanaService.exception.NotUniqueFloor;
import com.example.ToikanaService.exception.OrderNotFoundException;
import com.example.ToikanaService.mapper.FloorMapper;
import com.example.ToikanaService.repository.FloorRepository;
import com.example.ToikanaService.repository.OrderRepository;
import com.example.ToikanaService.repository.SewerRepository;
import com.example.ToikanaService.service.FloorService;
import com.example.ToikanaService.service.SewerService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FloorServiceImpl implements FloorService {
    final FloorRepository floorRepository;
    final SewerService sewerService;
    ModelMapper modelMapper;
    final SewerRepository sewerRepository;
    final OrderRepository orderRepository;
    @Override
    public FloorResponse save(FloorRequest t) {
        try {
            OrderEntity order = orderRepository.findById(t.getOrderId()).orElseThrow(() -> new OrderNotFoundException("Такого заказа нет", HttpStatus.BAD_REQUEST));
            FloorEntity floor = floorRepository.save(FloorEntity.builder()
                    .floorName(t.getFloorName())
                    .order(order)
                    .build());
            List<SewerEntity> sewerEntities = new ArrayList<>();
            for (int i = 0; i < t.getSewerId().size(); i++) {
                sewerEntities.add(sewerRepository.findById(t.getSewerId().get(i)).get());
            }
            Type listType = new TypeToken<List<SewerResponse>>(){}.getType();
            List<SewerResponse> sewerResponses = modelMapper.map(sewerEntities,listType);
            floor.setSewerEntities(sewerEntities);
            floorRepository.save(floor);
            return FloorResponse.builder()
                    .id(floor.getId())
                    .sewers(sewerResponses)
                    .floorName(t.getFloorName())
                    .clothType(order.getClothesType())
                    .unitPrice(order.getUnitPrice())
                    .amount(order.getAmount())
                    .build();
        }catch (Exception ignored){
            throw  new NotUniqueFloor("Одиннаковое название этажей", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<FloorResponse> getAll() {
        List<FloorEntity> floorEntities = floorRepository.findAll();
        List<FloorResponse> floorResponses = new ArrayList<>();
        for (FloorEntity floor: floorEntities) {
            floorResponses.add(FloorResponse.builder()
                    .sewers(sewerService.getAll())
                    .floorName(floor.getFloorName())
                    .id(floor.getId())
                    .amount(floor.getOrder().getAmount())
                    .unitPrice(floor.getOrder().getUnitPrice())
                    .clothType(floor.getOrder().getClothesType())
                    .build());
        }return floorResponses;
    }

    @Override
    public FloorResponse findById(Long id) {
        FloorEntity floor = floorRepository.getById(id);
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
        return FloorResponse.builder()
                .id(floor.getId())
                .sewers(sewerResponses)
                .floorName(floor.getFloorName())
                .clothType(floor.getOrder().getClothesType())
                .unitPrice(floor.getOrder().getUnitPrice())
                .amount(floor.getOrder().getAmount())
                .build();
    }
}
