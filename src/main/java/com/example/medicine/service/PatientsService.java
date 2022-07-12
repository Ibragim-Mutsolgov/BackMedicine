package com.example.medicine.service;

import com.example.medicine.domain.Patients;

import java.util.List;

public interface PatientsService {
    List<Patients> findAll();
    Patients findById(Long id);
    Patients save(Patients patients);
    void delete(Long id);
}