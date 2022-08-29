package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.DoctorsPosition;
import com.example.medicine.repository.DoctorsPositionRepository;
import com.example.medicine.service.DoctorsPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DoctorsPositionServiceImpl implements DoctorsPositionService {

    private DoctorsPositionRepository repository;

    @Override
    public ResponseEntity<List<DoctorsPosition>> findAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }
}
