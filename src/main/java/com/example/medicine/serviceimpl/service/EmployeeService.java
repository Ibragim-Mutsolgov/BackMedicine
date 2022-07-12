package com.example.medicine.serviceimpl.service;

import com.example.medicine.domain.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee save(Employee employee);
    void delete(Long id);
}