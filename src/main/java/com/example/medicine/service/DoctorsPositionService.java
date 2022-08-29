package com.example.medicine.service;

import com.example.medicine.model.DoctorsPosition;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorsPositionService {

    ResponseEntity<List<DoctorsPosition>> findAll();
}
