package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.model.Offices;
import com.example.medicine.model.Work;
import com.example.medicine.repository.WorkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

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
        ResponseEntity<List<Work>> list;

        // when
        list = service.findAll();

        // then
        assertEquals(list.getBody().size(), 0);
    }

    @Test
    void findById() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        response = service.findById(workSave.getWork_id());

        // then
        assertEquals(response.getBody(), workSave);
    }

    @Test
    void save() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        response = service.save(work);
        workSave = repository.getById(response.getBody().getWork_id());

        // then
        assertEquals(response.getBody(), workSave);
    }

    @Test
    public void putSave() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        work.setEmployee(new Employee(70L, ""));
        work.setOffices(new Offices(
                "205B"
        ));
        response = service.patchSave(workSave.getWork_id(), work);

        // then
        assertEquals(response.getBody().getEmployee(), new Employee(70L, ""));
    }

    @Test
    public void patchSave() {
        // given
        ResponseEntity<Work> response;
        Work workSave;

        // when
        workSave = repository.save(work);
        work.setEmployee(new Employee(69L, ""));
        response = service.putSave(workSave.getWork_id(), work);

        // then
        assertEquals(response.getBody().getEmployee(), new Employee(69L, ""));
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