package com.example.ToikanaService.service.impl;

import com.example.ToikanaService.dto.user.request.UserAuthRequest;
import com.example.ToikanaService.dto.user.request.UserRequest;
import com.example.ToikanaService.dto.user.request.UserUpdateRequest;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.entity.UserRoleEntity;
import com.example.ToikanaService.exception.NotUniqueRecord;
import com.example.ToikanaService.exception.UserNotFoundException;
import com.example.ToikanaService.exception.UserSignInException;
import com.example.ToikanaService.mapper.UserMapper;
import com.example.ToikanaService.repository.RoleRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.repository.UserRoleRepository;
import com.example.ToikanaService.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    final PasswordEncoder passwordEncoder;

    final RoleRepository roleRepository;

    final UserRoleRepository userRoleRepository;
    @Override
    public UserResponse save(UserRequest t) {
        try {
            UserEntity user = userRepository
                    .save(UserEntity.builder()
                            .login(t.getLogin())
                            .email(t.getEmail())
                            .password(passwordEncoder.encode(t.getPassword()))
                            .isActive(true)
                            .build());
            UserRoleEntity userRole = new UserRoleEntity();

            if (user.getLogin().equals("ADMIN")) {
                userRole.setRole(roleRepository.findFirstByNameRole("ROLE_ADMIN"));
            } else {
                userRole.setRole(roleRepository.findFirstByNameRole("ROLE_USER"));
            }
            userRole.setUser(userRepository.save(user));
            userRoleRepository.save(userRole);
            return UserMapper.INSTANCE.toUserResponse(user);
        } catch (Exception ignored) {
            throw new NotUniqueRecord("Не уникальный логин", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public String getToken(UserAuthRequest request) throws UserSignInException {
        UserEntity userEntity = userRepository.findByLoginOrEmail(request.getEmail());
        boolean isMatches = passwordEncoder.matches(request.getPassword(), userEntity.getPassword());
        if (isMatches) {
            return "Basic " + new String(Base64.getEncoder()
                    .encode((userEntity.getLogin() + ":" + request.getPassword()).getBytes()));
        } else {
            throw new UserSignInException("Неправильный логин или пароль!", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Boolean updateUser(UserUpdateRequest t) {
        UserEntity user = userRepository.getById(t.getId());
        if(t == null){
            throw new UserNotFoundException("Такого пользователя нет", HttpStatus.NOT_FOUND);
        }
        user.setEmail(t.getEmail());
        user.setPassword(t.getPassword());
        user.setLogin(t.getLogin());
        userRepository.save(user);
        return user.getId() != null ;
    }

    @Override
    public List<UserResponse> getAll() {
        return UserMapper.INSTANCE.toUsersResponse(userRepository.findAll());
    }

    @Override
    public UserResponse findById(Long id) {
        return UserMapper.INSTANCE.toUserResponse(userRepository.getById(id));
    }
}
