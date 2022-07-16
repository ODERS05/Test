package com.example.ToikanaService.boot;

import com.example.ToikanaService.entity.RoleEntity;
import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.entity.UserRoleEntity;
import com.example.ToikanaService.repository.RoleRepository;
import com.example.ToikanaService.repository.UserRepository;
import com.example.ToikanaService.repository.UserRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public ApplicationStartRunner(RoleRepository roleRepository, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        roleRepository.save(
//                RoleEntity.builder()
//                .nameRole("ROLE_ADMIN").build());
//        roleRepository.save(
//                RoleEntity.builder()
//                        .nameRole("ROLE_CUSTOMER")
//                        .build()
//        );

//        userRoleRepository.save(UserRoleEntity.builder()
//                .role(roleRepository.findFirstByNameRole("ROLE_CUSTOMER"))
//                .user(userRepository.save(UserEntity.builder()
//                        .login("Customer")
//                        .email("customer@gmail.com")
//                        .password("customer")
//                        .isActive(true).build())).build());
    }
}
