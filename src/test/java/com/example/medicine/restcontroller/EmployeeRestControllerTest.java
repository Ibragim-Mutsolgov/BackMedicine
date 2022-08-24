package com.example.medicine.restcontroller;

import com.example.medicine.model.Employee;
import com.example.medicine.repository.EmployeeRepository;
import com.example.medicine.service.EmployeeService;
import com.example.medicine.service.serviceimpl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerTest {

    private EmployeeRestController controller;

    private EmployeeService service;

    @Autowired
    private EmployeeRepository repository;

    private Employee employee;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        employee = new Employee(
                id,
                "Ivanov"
        );
        service = new EmployeeServiceImpl(repository);
        controller = new EmployeeRestController(service);
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Employee>> response;

        // when
        response = controller.findAll();

        // then
        assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        response = controller.findById(employeeSave.getEmployee_id());

        // then
        assertEquals(response.getBody(), employeeSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Employee> response;
        employee.setEmployee_id(29L);

        // when
        response = controller.save(employee);

        // then
        assertEquals(response.getBody(), employee);
    }

    @Test
    void resave() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        response = controller.putSave(employeeSave.getEmployee_id(), employeeSave);

        // then
        assertEquals(response.getBody(), employeeSave);
    }

    @Test
    void reSave() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        response = controller.patchSave(employeeSave.getEmployee_id(), employeeSave);

        // then
        assertEquals(response.getBody(), employeeSave);
    }

    @Test
    void deleteById() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        response = controller.deleteById(employeeSave.getEmployee_id());

        // then
        assertEquals(response.getBody(), employeeSave);
    }
}