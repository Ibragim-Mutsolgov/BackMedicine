package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Offices;
import com.example.medicine.repository.OfficesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

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
        ResponseEntity<List<Offices>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        response = service.findById(officesSave.getOffices_id());

        // then
        assertEquals(officesSave, response.getBody());
    }

    @Test
    void save() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;
        offices.setOffices_id(49L);

        // when
        response = service.save(offices);
        officesSave = repository.getById(response.getBody().getOffices_id());

        // then
        assertEquals(officesSave, offices);
    }

    @Test
    public void putSave() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        offices.setNumber_offices("adad");
        response = service.putSave(officesSave.getOffices_id(), offices);

        // then
        assertEquals(response.getBody().getNumber_offices(), "adad");
    }

    @Test
    public void patchSave() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        offices.setNumber_offices("adadd");
        response = service.patchSave(officesSave.getOffices_id(), offices);

        // then
        assertEquals(response.getBody().getNumber_offices(), "adadd");
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