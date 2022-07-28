package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class WorkServiceImplTest {

    @Mock
    private WorkRepository repository;

    private WorkServiceImpl service;

    private Work work;

    private Long id = 1L;

    @BeforeEach
    void setUp() {
        service = new WorkServiceImpl(repository);
        work = new Work(
                id,
                new Date(),
                new Date(),
                new Date(),
                null,
                null
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
        service.save(work);

        // when
        service.findById(id);

        // then
        verify(repository).findById(id);
    }

    @Test
    void save() {
        // given
        service.save(work);
        ArgumentCaptor<Work> argumentCaptor = ArgumentCaptor.forClass(Work.class);

        // when
        verify(repository).save(argumentCaptor.capture());
        Work workSave = argumentCaptor.getValue();

        // then
        assertThat(work).isEqualTo(workSave);
    }

    @Test
    void delete() {
        // given
        service.save(work);

        // when
        service.delete(id);

        // then
        verify(repository).deleteById(id);
    }
}