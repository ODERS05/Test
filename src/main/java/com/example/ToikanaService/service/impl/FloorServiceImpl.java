package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.entity.FloorEntity;
import com.example.ToikanaService.entity.SewerEntity;
import com.example.ToikanaService.exception.NotUniqueFloor;
import com.example.ToikanaService.mapper.FloorMapper;
import com.example.ToikanaService.repository.FloorRepository;
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
    @Override
    public FloorResponse save(FloorRequest t) {
        try {
            FloorEntity floor = floorRepository.save(FloorEntity.builder()
                    .floorName(t.getFloorName())
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
                    .sewers(sewerResponses)
                    .floorName(t.getFloorName())
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
                    .build());
        }
        return floorResponses;
    }

    @Override
    public FloorResponse findById(Long id) {
        return FloorMapper.INSTANCE.toFloorResponse(floorRepository.getById(id));
    }
}
