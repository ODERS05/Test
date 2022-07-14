package com.example.ToikanaService.repository;


import com.example.ToikanaService.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findFirstByNameRole(String nameRole);
}