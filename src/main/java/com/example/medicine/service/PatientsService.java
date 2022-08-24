package com.example.medicine.service;

import com.example.medicine.model.Patients;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientsService {

    ResponseEntity<List<Patients>> findAll();

    ResponseEntity<Patients> findById(Long id);

    ResponseEntity<Patients> save(Patients patients);

    ResponseEntity<Patients> putSave(Long id, Patients patients);

    ResponseEntity<Patients> patchSave(Long id, Patients patients);

    ResponseEntity<Patients> delete(Long id);
}