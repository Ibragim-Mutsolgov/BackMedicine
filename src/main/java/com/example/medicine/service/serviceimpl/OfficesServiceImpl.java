package com.example.medicine.service.serviceimpl;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.service.OfficesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OfficesServiceImpl implements OfficesService {

    private OfficesRepository officesRepository;

    @Override
    public List<Offices> findAll() {
        return officesRepository.findAll();
    }

    @Override
    public Offices findById(Long id) {
        return officesRepository.findById(id).get();
    }

    @Override
    public Offices save(Offices offices) {
        return officesRepository.save(offices);
    }

    @Override
    public void delete(Long id) {
        officesRepository.deleteById(id);
    }
}
