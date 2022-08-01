package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return peopleRepository.findById(id).get();
    }

    @Override
    public People save(People people) {
        return peopleRepository.save(people);
    }

    @Override
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
}