package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PeopleServiceImplTest {

    @Mock
    private PeopleRepository repository;

    private PeopleServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new PeopleServiceImpl(repository);
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
        People people = new People(
                1L,
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
        service.save(people);

        // when
        service.findById(1L);

        // then
        verify(repository).findById(1L);
    }

    @Test
    void save() {
        // given
        People people = new People(
                2L,
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
        service.save(people);

        // when
        ArgumentCaptor<People> argumentCaptor = ArgumentCaptor.forClass(People.class);

        verify(repository).save(argumentCaptor.capture());

        People peopleSave = argumentCaptor.getValue();

        // then
        assertThat(peopleSave).isEqualTo(people);
    }

    @Test
    void delete() {
        //given
        People people = new People(
                3L,
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
        service.save(people);

        // when
        service.delete(3L);

        // then
        verify(repository).deleteById(3L);
    }
}