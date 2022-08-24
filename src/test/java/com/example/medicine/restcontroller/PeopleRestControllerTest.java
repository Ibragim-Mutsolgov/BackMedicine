package com.example.medicine.restcontroller;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Patients;
import com.example.medicine.model.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import com.example.medicine.service.serviceimpl.PeopleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PeopleRestControllerTest {

    private PeopleService service;

    private PeopleRestController controller;

    @Autowired
    private PeopleRepository repository;

    private People people;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        people = new People(
                id,
                "Ivanov",
                "Ivan",
                "Ivanovich",
                new Date(),
                1,
                "Московская область, Химки",
                2563L,
                565656L,
                "Отдел УФМС",
                new Date(),
                "202-111",
                "Москва",
                "Лефортово",
                "Авиамоторная",
                "Авиамоторная 8а",
                null,
                null
        );
        service = new PeopleServiceImpl(repository);
        controller = new PeopleRestController(service);
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<People>> response;

        // when
        response = controller.findAll();

        // then
        assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    void findById() {
        // given
        People peopleSave;
        ResponseEntity<People> response;

        // when
        peopleSave = repository.save(people);
        response = controller.findById(peopleSave.getId());

        // then
        assertEquals(response.getBody(), peopleSave);
    }

    @Test
    void save() { // post
        // given
        ResponseEntity<People> response;
        people.setId(22L);

        // when
        response = controller.save(people);

        // then
        assertEquals(response.getBody(), people);
    }

    @Test
    void putSave() { // put
        // given
        ResponseEntity<People> response;
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        response = controller.putSave(peopleSave.getId(), peopleSave);

        // then
        assertEquals(response.getBody(), peopleSave);
    }

    @Test
    void reSave() { // patch
        // given
        ResponseEntity<People> response;
        People peopleSave;
        Employee employee = new Employee(
                2L,
                "205B"
        );
        Patients patients = new Patients(
                3L,
                "1235047",
                "45566",
                "12331"
        );

        // when
        people.setEmployee(employee);
        people.setPatients(patients);
        peopleSave = repository.save(people);
        peopleSave.setName("II");
        response = controller.patchSave(peopleSave.getId(), peopleSave);

        // then
        assertEquals(response.getBody(), peopleSave);
    }

    @Test
    void deleteById() {
        // given
        ResponseEntity<People> response;
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        response = controller.deleteById(peopleSave.getId());

        // then
        assertEquals(response.getBody(), peopleSave);
    }
}