package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Employee;
import com.example.medicine.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
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

        // when
        service.findAll();

        // then
        verify(repository).findAll();
    }

    @Test
    void findById() {
        // given
        service.save(employee);

        // when
        service.findById(id);

        // then
        verify(repository).findById(id);
    }

    @Test
    void save() {
        // given
        service.save(employee);
        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);

        // when
        verify(repository).save(argumentCaptor.capture());
        Employee employeeSave = argumentCaptor.getValue();

        // then
        assertEquals(employee, employeeSave);
    }

    @Test
    void delete() {
        // given
        service.save(employee);

        // when
        service.delete(id);

        // then
        verify(repository).deleteById(id);
    }
}