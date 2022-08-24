package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Patients;
import com.example.medicine.model.People;
import com.example.medicine.repository.PeopleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PeopleServiceImplTest {

    @Autowired
    private PeopleRepository repository;

    private PeopleServiceImpl service;

    private People people;

    private final Long id = 10L;

    @BeforeEach
    public void setUp() {
        service = new PeopleServiceImpl(repository);
        Patients patients = new Patients(
                11L,
                "",
                "",
                ""
        );
        Employee employee = new Employee(
                12L,
                ""
        );
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
                patients,
                employee
        );
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<People>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<People> resultPeople;
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        resultPeople = service.findById(peopleSave.getId());

        // then
        assertEquals(resultPeople.getBody(), peopleSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<People> response;
        People peopleSave;

        // when
        response = service.save(people);
        peopleSave = repository.getById(response.getBody().getId());

        // then
        assertEquals(peopleSave, response.getBody());
    }

    @Test
    public void putSave() {
        // given
        ResponseEntity<People> response;
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        people.setName("adad");
        response = service.putSave(peopleSave.getId(), people);

        // then
        assertEquals(response.getBody().getName(), "adad");
    }

    @Test
    public void patchSave() {
        // given
        ResponseEntity<People> response;
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        people.setName("adadd");
        response = service.patchSave(peopleSave.getId(), people);

        // then
        assertEquals(response.getBody().getName(), "adadd");
    }

    @Test
    void delete() {
        //given
        People peopleSave;
        // when
        peopleSave = repository.save(people);
        service.delete(peopleSave.getId());

        // then
        assertEquals(repository.findById(id), Optional.empty());
    }
}