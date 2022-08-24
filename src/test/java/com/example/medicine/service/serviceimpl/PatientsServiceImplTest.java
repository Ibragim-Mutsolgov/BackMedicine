package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Offices;
import com.example.medicine.model.Patients;
import com.example.medicine.model.People;
import com.example.medicine.repository.PatientsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class PatientsServiceImplTest {

    @Autowired
    private PatientsRepository repository;

    private PatientsServiceImpl service;

    private Patients patients;

    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new PatientsServiceImpl(repository);
        patients = new Patients(
                id,
                "54435",
                "455354",
                "45455"
        );
    }

    @Test
    void findAll() {
        // given
        ResponseEntity<List<Patients>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;
        patients.setPatients_id(6L);

        // when
        patientsSave = repository.save(patients);
        response = service.findById(patientsSave.getPatients_id());

        // then
        assertEquals(response.getBody(), patientsSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        response = service.save(patients);
        patientsSave = repository.getById(response.getBody().getPatients_id());

        // then
        assertEquals(response.getBody(), patientsSave);
    }

    @Test
    public void putSave() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        patients.setPatients_type_policy("adad");
        response = service.putSave(patientsSave.getPatients_id(), patients);

        // then
        assertEquals(response.getBody().getPatients_type_policy(), "adad");
    }

    @Test
    public void patchSave() {
        // given
        ResponseEntity<Patients> response;
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        patients.setPatients_type_policy("adadd");
        response = service.patchSave(patientsSave.getPatients_id(), patients);

        // then
        assertEquals(response.getBody().getPatients_type_policy(), "adadd");
    }

    @Test
    void delete() {
        // given
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        service.delete(patientsSave.getPatients_id());

        // then
        assertEquals(repository.findById(patientsSave.getPatients_id()), Optional.empty());
    }
}