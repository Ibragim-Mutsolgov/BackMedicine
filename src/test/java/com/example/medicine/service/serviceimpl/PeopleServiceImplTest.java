package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

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
    }

    @Test
    void findAll() {
        // given
        List<People> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        People peopleSave;

        // when
        peopleSave = repository.save(people);
        people = service.findById(peopleSave.getId());

        // then
        assertEquals(peopleSave, people);
    }

    @Test
    void save() {
        // given
        People peopleSave;

        // when
        peopleSave = service.save(people);
        people = repository.getById(peopleSave.getId());

        // then
        assertEquals(peopleSave, people);
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