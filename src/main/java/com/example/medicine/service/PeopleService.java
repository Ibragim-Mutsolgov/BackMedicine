package com.example.medicine.service;

import com.example.medicine.model.Employee;
import com.example.medicine.model.People;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PeopleService {

    ResponseEntity<List<People>> findAll();

    ResponseEntity<People> findById(Long id);

    ResponseEntity<People> findByEmployeeId(Employee employee);

    ResponseEntity<People> save(People people);

    ResponseEntity<People> putSave(Long id, People people);

    ResponseEntity<People> patchSave(Long id, People people);

    ResponseEntity<People> delete(Long id);
}