package com.example.medicine.repository;

import com.example.medicine.model.DoctorsPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorsPositionRepository extends JpaRepository<DoctorsPosition, Long> {
}
