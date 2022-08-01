package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Offices;
import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import com.example.medicine.service.WorkService;
import com.example.medicine.service.serviceimpl.WorkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class WorkRestControllerTest {

    private WorkService service;

    private WorkRestController controller;

    @Autowired
    private WorkRepository repository;

    @Mock
    private JmsTemplate jmsTemplate;

    private Work work;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        work = new Work(
                id,
                new Date(),
                new Date(),
                new Date(),
                null,
                null
        );
        service = new WorkServiceImpl(repository);
        controller = new WorkRestController(service, repository, jmsTemplate);
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Work>> response;

        // when
        response = controller.findAll();

        // then
        assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        response = controller.findById(id);

        // then
        assertEquals(response.getBody(), workSave);
    }

    @Test
    void save() { // post
        // given
        ResponseEntity<Work> response;

        // when
        response = controller.save(work);

        // then
        assertEquals(response.getBody(), work);
    }

    @Test
    void resave() { // put
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        work.setWork_id(35L);
        response = controller.resave(workSave.getWork_id(), work);

        // then
        assertEquals(response.getBody(), work);
    }

    @Test
    void reSave() { // patch
        // given
        ResponseEntity<Work> response;
        Work workSave;
        Employee employee = new Employee(
                2L,
                "II"
        );
        Offices offices = new Offices(
                3L,
                "205B"
        );
        work.setEmployee(employee);
        work.setOffices(offices);

        // when
        workSave = repository.save(work);
        employee = workSave.getEmployee();
        employee.setEmployee_name("305");
        work.setEmployee(employee);
        response = controller.reSave(workSave.getWork_id(), work);

        // then
        assertEquals(response.getBody(), workSave);
    }

    @Test
    void deleteById() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        response = controller.deleteById(workSave.getWork_id());

        // then
        assertEquals(response.getBody(), workSave);
    }
}