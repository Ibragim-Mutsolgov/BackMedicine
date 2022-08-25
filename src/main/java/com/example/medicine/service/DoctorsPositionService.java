package com.example.medicine.service;

import com.example.medicine.model.DoctorsPosition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorsPositionService {

    ResponseEntity<List<DoctorsPosition>> findAll();

    ResponseEntity<DoctorsPosition> findById(Long id);

    ResponseEntity<DoctorsPosition> save(DoctorsPosition doctorsPosition);

    ResponseEntity<DoctorsPosition> putSave(Long id, DoctorsPosition doctorsPosition);

    ResponseEntity<DoctorsPosition> patchSave(Long id, DoctorsPosition doctorsPosition);

    ResponseEntity<DoctorsPosition> delete(Long id);
}
