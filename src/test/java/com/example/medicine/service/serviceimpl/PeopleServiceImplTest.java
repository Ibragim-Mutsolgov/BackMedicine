package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
        Long id = 1L;
        People people = new People(
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
        service.save(people);

        // when
        service.findById(id);

        // then
        verify(repository).findById(id);
    }

    @Test
    void save() {
        // given
        Long id = 2L;
        People people = new People(
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
        Long id = 3L;
        People people = new People(
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
        service.save(people);

        // when
        service.delete(id);

        // then
        verify(repository).deleteById(id);
    }
}