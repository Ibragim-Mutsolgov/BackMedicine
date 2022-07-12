package com.example.medicine.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.serviceimpl.service.PeopleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    private PeopleRepository peopleRepository;

    @Override
    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    @Override
    public People findById(Long id) {
        People people = peopleRepository.findById(id).get();
        log.info("IN PeopleServiceImpl: PEOPLE " + people + " FOUND");
        return people;
    }

    @Override
    public People save(People people) {
        peopleRepository.save(people);
        log.info("IN PeopleServiceImpl: PEOPLE " + people + " SAVED");
        return people;
    }

    @Override
    public void delete(Long id) {
        People people = peopleRepository.findById(id).get();
        peopleRepository.deleteById(id);
        log.info("IN PeopleServiceImpl: PEOPLE " + people + " DELETED");
    }
}