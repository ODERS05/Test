package com.example.ToikanaService.service;


import com.example.ToikanaService.dto.user.request.UserAuthRequest;

public interface UserService extends BaseService{
    String getToken(UserAuthRequest request);
}
