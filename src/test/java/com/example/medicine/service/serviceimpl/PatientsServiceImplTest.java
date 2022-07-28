package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class PatientsServiceImplTest {

    @Mock
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

        // when
        service.findAll();

        // then
        verify(repository).findAll();
    }

    @Test
    void findById() {
        // given
        service.save(patients);

        // when
        service.findById(id);

        // then
        verify(repository).findById(id);
    }

    @Test
    void save() {
        // given
        service.save(patients);
        ArgumentCaptor<Patients> argumentCaptor = ArgumentCaptor.forClass(Patients.class);

        // when
        verify(repository).save(argumentCaptor.capture());
        Patients patientsSave = argumentCaptor.getValue();

        // then
        assertThat(patients).isEqualTo(patientsSave);
    }

    @Test
    void delete() {
        // given
        service.save(patients);

        // when
        service.delete(id);

        // then
        verify(repository).deleteById(id);
    }
}