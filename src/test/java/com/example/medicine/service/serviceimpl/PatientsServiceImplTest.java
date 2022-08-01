package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

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
        List<Patients> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        patients = service.findById(patientsSave.getPatients_id());

        // then
        assertEquals(patientsSave, patients);
    }

    @Test
    void save() {
        // given
        Patients patientsSave;

        // when
        patientsSave = service.save(patients);
        patients = repository.getById(patientsSave.getPatients_id());

        // then
        assertEquals(patientsSave, patients);
    }

    @Test
    void delete() {
        // given
        Patients patientsSave;

        // when
        patientsSave = repository.save(patients);
        service.delete(id);

        // then
        assertEquals(repository.findById(patientsSave.getPatients_id()), Optional.empty());
    }
}