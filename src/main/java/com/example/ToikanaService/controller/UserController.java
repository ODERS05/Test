package com.example.ToikanaService.controller;

import com.example.ToikanaService.dto.user.request.UserAuthRequest;
import com.example.ToikanaService.dto.user.request.UserRequest;
import com.example.ToikanaService.dto.user.response.UserResponse;
import com.example.ToikanaService.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CrossOrigin(origins = "*", maxAge = 8600)
public class UserController {
    final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @SneakyThrows
    @PostMapping("/auto")
    public String auto(@RequestBody UserAuthRequest request) {
        return userService.getToken(request);
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }
    @PutMapping("/update")
    public Boolean update(@RequestBody UserRequest request){
        return userService.updateUser(request);
    }
}
