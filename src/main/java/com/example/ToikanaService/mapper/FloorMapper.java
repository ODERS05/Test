package com.example.ToikanaService.mapper;

import com.example.ToikanaService.dto.floor.request.FloorRequest;
import com.example.ToikanaService.dto.floor.response.FloorResponse;
import com.example.ToikanaService.entity.FloorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FloorMapper {
    FloorMapper INSTANCE = Mappers.getMapper(FloorMapper.class);

    FloorEntity toFloorEntity(FloorRequest floorRequest);
    @Mappings(value = {
            @Mapping(source = "userEntities", target = "users")
    })
    FloorResponse toFloorResponse(FloorEntity floor);
    List<FloorResponse> toFloorsResponse(List<FloorEntity> entities);
}
