package com.example.medicine.service;

import com.example.medicine.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {

    ResponseEntity<List<Employee>> findAll();

    ResponseEntity<Employee> findById(Long id);

    ResponseEntity<Employee> save(Employee employee);

    ResponseEntity<Employee> putSave(Long id, Employee employee);

    ResponseEntity<Employee> patchSave(Long id, Employee employee);

    ResponseEntity<Employee> delete(Long id);
}