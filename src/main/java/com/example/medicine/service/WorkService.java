package com.example.medicine.service;

import com.example.medicine.domain.Work;

import java.util.List;

public interface WorkService {

    List<Work> findAll();

    Work findById(Long id);

    Work save(Work work);

    void delete(Long id);
}
