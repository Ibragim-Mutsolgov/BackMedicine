package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

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
                "Ivanov Ivan Ivanovich",
                null
        );
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Employee>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Employee> resultEmployee;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        resultEmployee = service.findById(employeeSave.getEmployee_id());

        // then
        assertEquals(resultEmployee.getBody(), employeeSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Employee> resultEmployee;
        Employee employeeSave;

        // when
        resultEmployee = service.save(employee);
        employeeSave = repository.getById(resultEmployee.getBody().getEmployee_id());

        // then
        assertEquals(resultEmployee.getBody(), employeeSave);
    }

    @Test
    public void putSave() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        employee.setEmployee_name("adad");
        response = service.putSave(employeeSave.getEmployee_id(), employee);

        // then
        assertEquals(response.getBody().getEmployee_name(), "adad");
    }

    @Test
    public void patchSave() {
        // given
        ResponseEntity<Employee> response;
        Employee employeeSave;

        // when
        employeeSave = repository.save(employee);
        employee.setEmployee_name("adadd");
        response = service.patchSave(employeeSave.getEmployee_id(), employee);

        // then
        assertEquals(response.getBody().getEmployee_name(), "adadd");
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