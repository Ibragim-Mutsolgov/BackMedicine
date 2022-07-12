package com.example.medicine.serviceimpl;

import com.example.medicine.domain.Employee;
import com.example.medicine.repository.EmployeeRepository;
import com.example.medicine.serviceimpl.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        log.info("IN EmployeeServiceImpl: EMPLOYEE " + employee + " FOUND");
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        log.info("IN EmployeeServiceImpl: EMPLOYEE " + employee + " SAVED");
        return employee;
    }

    @Override
    public void delete(Long id) {
        Employee employee = employeeRepository.findById(id).get();
        employeeRepository.deleteById(id);
        log.info("IN EmployeeServiceImpl: EMPLOYEE " + employee + " DELETED");
    }
}