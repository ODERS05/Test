package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.entity.FloorEntity;
import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.exception.NotUniqueFloor;
import com.example.ToikanaService.mapper.FloorMapper;
import com.example.ToikanaService.mapper.UserMapper;
import com.example.ToikanaService.repository.FloorRepository;
import com.example.ToikanaService.repository.OrderRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.service.FloorService;
import com.example.ToikanaService.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FloorServiceImpl implements FloorService {
    final FloorRepository floorRepository;
    final UserService userService;
    final OrderRepository orderRepository;
    @Override
    public FloorResponse save(FloorRequest t) {
        try {
            FloorEntity floor = FloorMapper.INSTANCE.toFloorEntity(t);
//            List<UserEntity> userEntities = new ArrayList<>();
//            for (int i = 0; i < t.getUser_id().size(); i++) {
//                userEntities.add(UserMapper.INSTANCE.toUserEntity(userService.findById(t.getUser_id().get(i))));
//            }
//            floor.setUserEntities(userEntities);
            return FloorMapper.INSTANCE.toFloorResponse(floor);
        }catch (Exception ignored){
            throw  new NotUniqueFloor("Одинноковое название этажей", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<FloorResponse> getAll() {
        return FloorMapper.INSTANCE.toFloorsResponse(floorRepository.findAll());
    }

    @Override
    public FloorResponse findById(Long id) {
        return null;
    }
}
