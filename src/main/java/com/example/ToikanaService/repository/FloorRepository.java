package com.example.ToikanaService.repository;

import com.example.ToikanaService.entity.FloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Long> {
}
