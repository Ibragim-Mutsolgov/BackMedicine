package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class OfficesServiceImplTest {

    @Autowired
    private OfficesRepository repository;

    private OfficesServiceImpl service;

    private Offices offices;

    private final Long id = 1L;

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
        List<Offices> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        offices = service.findById(officesSave.getOffices_id());

        // then
        assertEquals(officesSave, offices);
    }

    @Test
    void save() {
        // given
        Offices officesSave;

        // when
        officesSave = service.save(offices);
        offices = repository.getById(officesSave.getOffices_id());

        // then
        assertEquals(officesSave, offices);
    }

    @Test
    void delete() {
        // given
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        service.delete(officesSave.getOffices_id());

        // then
        assertEquals(repository.findById(officesSave.getOffices_id()), Optional.empty());
    }
}