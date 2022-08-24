package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class PatientsServiceImpl implements PatientsService {

    private PatientsRepository patientsRepository;

    @Override
    public ResponseEntity<List<Patients>> findAll() {
        return ResponseEntity.ok().body(patientsRepository.findAll());
    }

    @Override
    public ResponseEntity<Patients> findById(Long id) {
        return patientsRepository.findById(id)
                .map(patients -> ResponseEntity.ok().body(patients))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> save(Patients patients) {
        return ResponseEntity.ok().body(patientsRepository.save(patients));
    }

    @Override
    public ResponseEntity<Patients> putSave(Long id, Patients patients) {
        return patientsRepository.findById(id)
                .map(patientsSave -> {
                    patientsSave.setPatients_id(id);
                    patientsSave.setPatients_policy(patients.getPatients_policy());
                    patientsSave.setPatients_type_policy(patients.getPatients_type_policy());
                    patientsSave.setPatients_number_ambulant_card(patients.getPatients_number_ambulant_card());
                    return ResponseEntity.ok().body(patientsRepository.save(patientsSave));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> patchSave(Long id, Patients patients) {
        return patientsRepository.findById(id)
                .map(patientsSave -> {
                    if(patients.getPatients_number_ambulant_card() != null){
                        patientsSave.setPatients_number_ambulant_card(patients.getPatients_number_ambulant_card());
                    }
                    if(patients.getPatients_policy() != null){
                        patientsSave.setPatients_policy(patients.getPatients_policy());
                    }
                    if(patients.getPatients_type_policy() != null){
                        patientsSave.setPatients_type_policy(patients.getPatients_type_policy());
                    }
                    return ResponseEntity.ok().body(patientsRepository.save(patientsSave));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> delete(Long id) {
        return patientsRepository.findById(id)
                .map(patients -> {
                    patientsRepository.deleteById(patients.getPatients_id());
                    return ResponseEntity.ok().body(patients);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}