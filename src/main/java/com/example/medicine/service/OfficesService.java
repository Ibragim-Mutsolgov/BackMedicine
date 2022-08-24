package com.example.medicine.service;

import com.example.medicine.model.Offices;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfficesService {

    ResponseEntity<List<Offices>> findAll();

    ResponseEntity<Offices> findById(Long id);

    ResponseEntity<Offices> save(Offices offices);

    ResponseEntity<Offices> putSave(Long id, Offices offices);

    ResponseEntity<Offices> patchSave(Long id, Offices offices);

    ResponseEntity<Offices> delete(Long id);
}
