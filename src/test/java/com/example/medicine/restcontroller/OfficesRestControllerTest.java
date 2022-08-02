package com.example.medicine.restcontroller;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.service.OfficesService;
import com.example.medicine.service.serviceimpl.OfficesServiceImpl;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class OfficesRestControllerTest {

    private OfficesRestController controller;

    private OfficesService service;

    @Autowired
    private OfficesRepository repository;

    @Mock
    private JmsTemplate jmsTemplate;

    private Offices offices;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        offices = new Offices(
                id,
                "105B"
        );
        service = new OfficesServiceImpl(repository);
        controller = new OfficesRestController(service, repository, jmsTemplate);
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Offices>> response;

        // when
        response = controller.findAll();

        // then
        assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        response = controller.findById(officesSave.getOffices_id());

        // then
        assertEquals(response.getBody(), officesSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Offices> response;
        offices.setOffices_id(8L);

        // when
        response = controller.save(offices);

        // then
        assertEquals(response.getBody(), offices);
    }

    @Test
    void resave() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        response = controller.resave(officesSave.getOffices_id(), officesSave);

        // then
        assertEquals(response.getBody(), officesSave);
    }

    @Test
    void reSave() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        response = controller.reSave(officesSave.getOffices_id(), officesSave);

        // then
        assertEquals(response.getBody(), officesSave);
    }

    @Test
    void deleteById() {
        // given
        ResponseEntity<Offices> response;
        Offices officesSave;

        // when
        officesSave = repository.save(offices);
        response = controller.deleteById(officesSave.getOffices_id());

        // then
        assertEquals(response.getBody(), officesSave);
    }
}