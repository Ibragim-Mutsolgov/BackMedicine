package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class WorkServiceImplTest {

    @Autowired
    private WorkRepository repository;

    private WorkServiceImpl service;

    private Work work;

    private final Long id = 1L;

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
        List<Work> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.size(), 0);
    }

    @Test
    void findById() {
        // given
        Work workSave;

        // when
        workSave = repository.save(work);
        work = service.findById(workSave.getWork_id());

        // then
        assertEquals(work, workSave);
    }

    @Test
    void save() {
        // given
        Work workSave;

        // when
        workSave = service.save(work);
        work = repository.getById(workSave.getWork_id());

        // then
        assertEquals(workSave, work);
    }

    @Test
    void delete() {
        // given
        Work workSave;

        // when
        workSave = repository.save(work);
        service.delete(workSave.getWork_id());

        // then
        assertEquals(repository.findById(workSave.getWork_id()), Optional.empty());
    }
}