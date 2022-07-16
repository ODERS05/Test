package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.entity.FloorEntity;
import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.exception.NotUniqueFloor;
import com.example.ToikanaService.mapper.FloorMapper;
import com.example.ToikanaService.repository.FloorRepository;
import com.example.ToikanaService.repository.OrderRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.service.FloorService;
import com.example.ToikanaService.service.UserService;
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
    final UserRepository userRepository;
    ModelMapper modelMapper;
    final UserService userService;
    @Override
    public FloorResponse save(FloorRequest t) {
        try {
            FloorEntity floor = floorRepository.save(FloorEntity.builder()
                    .floorName(t.getFloorName())
                    .build());
            List<UserEntity> userEntities = new ArrayList<>();
            for (int i = 0; i < t.getUserId().size(); i++) {
                userEntities.add(userRepository.findById(t.getUserId().get(i)).get());
            }
            Type listType = new TypeToken<List<UserResponse>>(){}.getType();
            List<UserResponse> userResponsesList = modelMapper.map(userEntities,listType);
            floor.setUserEntities(userEntities);
            floorRepository.save(floor);
            return FloorResponse.builder()
                    .users(userResponsesList)
                    .floorName(t.getFloorName())
                    .build();
        }catch (Exception ignored){
            throw  new NotUniqueFloor("Одиннаковое название этажей", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<FloorResponse> getAll() {
        return FloorMapper.INSTANCE.toFloorsResponse(floorRepository.findAll());
    }

    @Override
    public FloorResponse findById(Long id) {
        return FloorMapper.INSTANCE.toFloorResponse(floorRepository.getById(id));
    }
}
