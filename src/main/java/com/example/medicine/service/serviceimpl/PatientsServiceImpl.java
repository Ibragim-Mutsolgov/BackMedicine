package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.restcontroller.PatientsRestController;
import com.example.medicine.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


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
                .map(patients -> {
                    addHateos(patients);
                    return ResponseEntity.ok().body(patients);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> save(Patients patients) {
        patients = patientsRepository.save(patients);
        addHateos(patients);
        return ResponseEntity.ok().body(patientsRepository.save(patients));
    }

    @Override
    public ResponseEntity<Patients> putSave(Long id, Patients patients) {
        return patientsRepository.findById(id)
                .map(patientsSave -> {
                    patientsSave.setPatients_id(id);
                    patientsSave.setPeopleId(patients.getPeopleId());
                    addHateos(patientsSave);
                    return ResponseEntity.ok().body(patientsRepository.save(patientsSave));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> patchSave(Long id, Patients patients) {
        return patientsRepository.findById(id)
                .map(patientsSave -> {
                    if(patients.getPeopleId() != null){
                        patientsSave.setPeopleId(patients.getPeopleId());
                    }
                    addHateos(patientsSave);
                    return ResponseEntity.ok().body(patientsRepository.save(patientsSave));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Patients> delete(Long id) {
        return patientsRepository.findById(id)
                .map(patients -> {
                    addHateos(patients);
                    patientsRepository.deleteById(patients.getPatients_id());
                    return ResponseEntity.ok().body(patients);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Patients addHateos(Patients patients) {
        patients.add(
                linkTo(methodOn(PatientsRestController.class)
                        .findAll()).withRel("findAll"),
                linkTo(methodOn(PatientsRestController.class)
                        .findById(patients.getPatients_id())).withRel("findById"),
                linkTo(methodOn(PatientsRestController.class)
                        .save(patients)).withRel("save"),
                linkTo(methodOn(PatientsRestController.class)
                        .putSave(patients.getPatients_id(), patients)).withRel("putSave"),
                linkTo(methodOn(PatientsRestController.class)
                        .patchSave(patients.getPatients_id(), patients)).withRel("patchSave"),
                linkTo(methodOn(PatientsRestController.class)
                        .deleteById(patients.getPatients_id())).withRel("deleteById")
        );
        return patients;
    }
}