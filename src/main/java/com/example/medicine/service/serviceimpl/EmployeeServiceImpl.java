package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.repository.EmployeeRepository;
import com.example.medicine.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @Override
    public ResponseEntity<Employee> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> ResponseEntity.ok().body(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> save(Employee employee) {
        return ResponseEntity.ok().body(employeeRepository.save(employee));
    }

    @Override
    public ResponseEntity<Employee> putSave(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(employeeSave -> {
                    employeeSave.setEmployee_id(id);
                    employeeSave.setEmployee_name(employee.getEmployee_name());
                    return ResponseEntity.ok().body(employeeRepository.save(employeeSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> patchSave(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(employeeSave -> {
                    employeeSave.setEmployee_name(employee.getEmployee_name());
                    return ResponseEntity.ok().body(employeeRepository.save(employeeSave));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> delete(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.deleteById(employee.getEmployee_id());
                    return ResponseEntity.ok().body(employee);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}