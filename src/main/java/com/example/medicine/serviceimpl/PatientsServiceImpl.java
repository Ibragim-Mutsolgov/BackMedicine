package com.example.medicine.serviceimpl;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.serviceimpl.service.PatientsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PatientsServiceImpl implements PatientsService {

    private PatientsRepository patientsRepository;

    @Override
    public List<Patients> findAll() {
        return patientsRepository.findAll();
    }

    @Override
    public Patients findById(Long id) {
        Patients patients = patientsRepository.findById(id).get();
        log.info("IN PatientsServiceImpl: PATIENTS " + patients + " FOUND");
        return patients;
    }

    @Override
    public Patients save(Patients patients) {
        patientsRepository.save(patients);
        log.info("IN PatientsServiceImpl: PATIENTS " + patients + " SAVED");
        return patients;
    }

    @Override
    public void delete(Long id) {
        Patients patients = patientsRepository.findById(id).get();
        patientsRepository.deleteById(id);
        log.info("IN PatientsServiceImpl: PATIENTS " + patients + " DELETED");
    }
}