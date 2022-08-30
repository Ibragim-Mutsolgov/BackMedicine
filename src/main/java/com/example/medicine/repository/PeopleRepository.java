package com.example.medicine.repository;

import com.example.medicine.model.Employee;
import com.example.medicine.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    Optional<People> findPeopleByEmployee(Employee employee);
}