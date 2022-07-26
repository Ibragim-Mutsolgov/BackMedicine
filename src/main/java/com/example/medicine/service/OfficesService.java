package com.example.medicine.service;

import com.example.medicine.domain.Offices;

import java.util.List;

public interface OfficesService {

    List<Offices> findAll();

    Offices findById(Long id);

    Offices save(Offices offices);

    void delete(Long id);
}
