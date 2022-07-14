package com.example.ToikanaService.service.impl;
import com.example.ToikanaService.dto.role.request.RoleRequest;
import com.example.ToikanaService.dto.role.response.RoleResponse;
import com.example.ToikanaService.exception.NotUniqueRecord;
import com.example.ToikanaService.mapper.RoleMapper;
import com.example.ToikanaService.repository.RoleRepository;
import com.example.ToikanaService.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleServiceImpl implements RoleService {
    final RoleRepository roleRepository;

    @Override
    public RoleResponse save(RoleRequest roleRequestDto) {
        try {
            roleRequestDto.setName("ROLE_" + roleRequestDto.getName().toUpperCase());
            System.out.println(roleRequestDto.getName());
            return RoleMapper.INSTANCE
                    .toResponseDto(roleRepository.save(RoleMapper.INSTANCE.toRoleEntity(roleRequestDto)));
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Одинноковая роль", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<RoleResponse> getAll() {
        return RoleMapper.INSTANCE.toRolesDto(roleRepository.findAll());
    }

    @Override
    public RoleResponse findById(Long id) {
        return RoleMapper.INSTANCE.toResponseDto(roleRepository.getById(id));
    }
}