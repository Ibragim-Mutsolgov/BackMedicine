package com.example.medicine.serviceimpl;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientsServiceImpl implements PatientsService {
    private PatientsRepository patientsRepository;

    @Override
    public List<Patients> findAll() {
        return patientsRepository.findAll();
    }

    @Override
    public Patients findById(Long id) {
        return patientsRepository.findById(id).get();
    }

    @Override
    public Patients save(Patients patients) {
        return patientsRepository.save(patients);
    }

    @Override
    public void delete(Long id) {
        patientsRepository.deleteById(id);
    }
}