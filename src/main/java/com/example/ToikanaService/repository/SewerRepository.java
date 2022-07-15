package com.example.ToikanaService.repository;

import com.example.ToikanaService.entity.SewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SewerRepository extends JpaRepository<SewerEntity, Long> {
}
