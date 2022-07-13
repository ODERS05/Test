package com.example.ToikanaService.mapper;

import com.example.ToikanaService.dto.user.request.UserAuthRequest;
import com.example.ToikanaService.dto.user.request.UserRequest;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse toUserResponse(UserEntity user);

    List<UserResponse> toUsersResponse(List<UserEntity> users);

    UserAuthRequest toUserAuth(UserEntity user);

    UserEntity toUserEntity(UserResponse userResponse);

    default void test(UserRequest userRequest){

    }
}
