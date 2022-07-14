package com.example.ToikanaService.repository;

import com.example.ToikanaService.entity.UserEntity;
import com.example.ToikanaService.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
}
