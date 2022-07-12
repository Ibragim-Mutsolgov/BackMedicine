package com.example.medicine.repository;

import com.example.medicine.domain.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    People findBySurname(String surname);
}