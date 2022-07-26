package com.example.medicine.service;

import com.example.medicine.domain.People;

import java.util.List;

public interface PeopleService {

    List<People> findAll();

    People findById(Long id);

    People save(People people);

    void delete(Long id);
}