package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Employee;
import com.example.medicine.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeRepository repository;

    private EmployeeServiceImpl service;

    private Employee employee;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new EmployeeServiceImpl(repository);
        employee = new Employee(
                id,
                "Ivanov Ivan Ivanovich"
        );
    }

    @Test
    void findAll() {
        // given
        List<Employee> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        employee = service.findById(employeeSave.getEmployee_id());

        // then
        assertEquals(employeeSave, employee);
    }

    @Test
    void save() {
        // given
        Employee employeeSave;

        // when
        employeeSave = service.save(employee);
        employee = repository.getById(employeeSave.getEmployee_id());

        // then
        assertEquals(employeeSave, employee);
    }

    @Test
    void delete() {
        // given
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        service.delete(employeeSave.getEmployee_id());

        // then
        assertEquals(repository.findById(employeeSave.getEmployee_id()), Optional.empty());
    }
}