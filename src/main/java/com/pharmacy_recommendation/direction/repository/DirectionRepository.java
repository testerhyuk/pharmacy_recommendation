package com.pharmacy_recommendation.direction.repository;

import com.pharmacy_recommendation.direction.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
