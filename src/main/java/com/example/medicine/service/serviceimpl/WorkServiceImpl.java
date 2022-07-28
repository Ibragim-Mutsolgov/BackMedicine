package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import com.example.medicine.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WorkServiceImpl implements WorkService {

    private WorkRepository workRepository;

    @Override
    public List<Work> findAll() {
        return workRepository.findAll();
    }

    @Override
    public Work findById(Long id) {
        return workRepository.findById(id).orElse(null);
    }

    @Override
    public Work save(Work work) {
        return workRepository.save(work);
    }

    @Override
    public void delete(Long id) {
        workRepository.deleteById(id);
    }
}
