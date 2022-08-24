package com.example.medicine.repository;

import com.example.medicine.model.Offices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficesRepository extends JpaRepository<Offices, Long> {
}
