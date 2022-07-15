package com.example.ToikanaService.service;


import com.example.ToikanaService.dto.user.request.UserAuthRequest;
import com.example.ToikanaService.dto.user.request.UserRequest;
import com.example.ToikanaService.dto.user.request.UserUpdateRequest;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.exception.UserSignInException;

public interface UserService extends BaseService<UserResponse, UserRequest> {
    String getToken(UserAuthRequest request) throws UserSignInException;
    Boolean updateUser(UserUpdateRequest t);
}
