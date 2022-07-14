package com.example.ToikanaService.mapper;


import com.example.ToikanaService.dto.role.request.RoleRequest;
import com.example.ToikanaService.dto.role.response.RoleResponse;
import com.example.ToikanaService.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleEntity toRoleEntity(RoleRequest roleRequestDto);

    RoleEntity toRoleEntity(RoleResponse roleResponseDto);

    RoleResponse toResponseDto(RoleEntity category);

    List<RoleResponse> toRolesDto(List<RoleEntity> categories);
}