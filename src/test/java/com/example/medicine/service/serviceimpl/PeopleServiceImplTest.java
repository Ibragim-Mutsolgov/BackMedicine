package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PeopleServiceImplTest {

    @Mock
    private PeopleRepository repository;

    private AutoCloseable autoCloseable;

    private PeopleServiceImpl service;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        service = new PeopleServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAll() {
        // given
        List<People> list = service.findAll();

        // when

        // then
        assertNotNull(list);
    }

    @Test
    void findById() {
        // given
        People people = new People(
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
        people = service.save(people);

        // when
        People peopleSave = service.findById(people.getId());

        // then
        assertEquals(people, peopleSave);
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
        //given
        People people = new People(
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
        people = service.save(people);
        service.delete(people.getId());

        //when
        People peopleSave = service.findById(people.getId());

        //then
        assertEquals(null, peopleSave);
    }
}