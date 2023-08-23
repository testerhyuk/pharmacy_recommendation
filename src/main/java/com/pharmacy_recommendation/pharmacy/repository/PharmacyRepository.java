package com.pharmacy_recommendation.pharmacy.repository;

import com.pharmacy_recommendation.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
