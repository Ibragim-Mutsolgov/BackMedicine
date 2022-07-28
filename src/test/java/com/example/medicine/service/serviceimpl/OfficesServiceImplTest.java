package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OfficesServiceImplTest {

    @Mock
    private OfficesRepository repository;

    private OfficesServiceImpl service;

    private Offices offices;

    private Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new OfficesServiceImpl(repository);
        offices = new Offices(
                id,
                "205B"
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
        service.save(offices);

        // when
        service.findById(id);

        // then
        verify(repository).findById(id);
    }

    @Test
    void save() {
        // given
        service.save(offices);
        ArgumentCaptor<Offices> argumentCaptor = ArgumentCaptor.forClass(Offices.class);

        // when
        verify(repository).save(argumentCaptor.capture());
        Offices officesSave = argumentCaptor.getValue();

        // then
        assertThat(offices).isEqualTo(officesSave);
    }

    @Test
    void delete() {
        // given
        service.save(offices);

        // when
        service.delete(id);

        // then
        verify(repository).deleteById(id);
    }
}