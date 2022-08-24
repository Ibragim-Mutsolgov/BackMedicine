package com.example.medicine.service;

import com.example.medicine.model.Work;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WorkService {

    ResponseEntity<List<Work>> findAll();

    ResponseEntity<Work> findById(Long id);

    ResponseEntity<Work> save(Work work);

    ResponseEntity<Work> putSave(Long id, Work work);

    ResponseEntity<Work> patchSave(Long id, Work work);

    ResponseEntity<Work> delete(Long id);
}
