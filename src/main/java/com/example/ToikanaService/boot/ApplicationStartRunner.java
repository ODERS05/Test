package com.example.ToikanaService.boot;

import com.example.ToikanaService.entity.RoleEntity;
import com.example.ToikanaService.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public ApplicationStartRunner(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        roleRepository.save(
//                RoleEntity.builder()
//                .nameRole("ROLE_ADMIN").build());
//        roleRepository.save(
//                RoleEntity.builder()
//                        .nameRole("ROLE_USER")
//                        .build()
//        );
    }
}
