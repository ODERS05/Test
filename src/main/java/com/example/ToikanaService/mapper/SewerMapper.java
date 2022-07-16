package com.example.ToikanaService.mapper;

import com.example.ToikanaService.dto.sewer.request.SewerRequest;
import com.example.ToikanaService.dto.sewer.response.SewerResponse;
import com.example.ToikanaService.entity.SewerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SewerMapper {
    SewerMapper INSTANCE = Mappers.getMapper(SewerMapper.class);
    SewerEntity toSewerEntity(SewerRequest request);
    SewerResponse toSewerResponse(SewerEntity sewer);

    List<SewerResponse> toSewersResponse(List<SewerEntity> sewerEntities);
}
