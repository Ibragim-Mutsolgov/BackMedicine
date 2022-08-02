package com.example.medicine.restcontroller;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.service.PatientsService;
import com.example.medicine.service.serviceimpl.PatientsServiceImpl;
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
class PatientsRestControllerTest {

    private PatientsRestController controller;

    private PatientsService service;

    @Autowired
    private PatientsRepository repository;

    @Mock
    private JmsTemplate jmsTemplate;

    private Patients patients;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        patients = new Patients(
                id,
                "123314",
                "456322",
                "44566"
        );
        service = new PatientsServiceImpl(repository);
        controller = new PatientsRestController(service, repository, jmsTemplate);
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Patients>> response;

        // when
        response = controller.findAll();

        // then
        assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        response = controller.findById(patientsSave.getPatients_id());

        // then
        assertEquals(response.getBody(), patientsSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Patients> response;
        patients.setPatients_id(4L);

        // when
        response = controller.save(patients);

        // then
        assertEquals(response.getBody(), patients);
    }

    @Test
    void resave() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        patientsSave.setPatients_type_policy("2323132");
        response = controller.resave(patientsSave.getPatients_id(), patientsSave);

        // then
        assertEquals(response.getBody(), patientsSave);
    }

    @Test
    void reSave() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        response = controller.reSave(patientsSave.getPatients_id(), patientsSave);

        // then
        assertEquals(response.getBody(), patientsSave);
    }

    @Test
    void deleteById() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        response = controller.deleteById(patientsSave.getPatients_id());

        // then
        assertEquals(response.getBody(), patientsSave);
    }
}