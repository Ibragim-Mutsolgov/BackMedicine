package com.example.medicine.restcontroller;

import com.example.medicine.domain.Patients;
import com.example.medicine.repository.PatientsRepository;
import com.example.medicine.service.PatientsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
public class PatientsRestController {
    private PatientsService patientsService;
    private PatientsRepository patientsRepository;

    @GetMapping
    public ResponseEntity<List<Patients>> findAll(){
        return ResponseEntity.ok(patientsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> findById(@PathVariable Long id){
        return patientsRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patients> save(@RequestBody Patients patients){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientsRepository.save(patients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patients> reSave(
            @PathVariable Long id,
            @RequestBody Patients patients){
        return patientsRepository.findById(id)
                .map(patients1 -> {
                    if(patients.getPatients_number_ambulant_card() != null){
                        patients1.setPatients_number_ambulant_card(patients.getPatients_number_ambulant_card());
                    }
                    if(patients.getPatients_policy() != null){
                        patients1.setPatients_policy(patients.getPatients_policy());
                    }
                    if(patients.getPatients_type_policy() != null){
                        patients1.setPatients_type_policy(patients.getPatients_type_policy());
                    }
                    return ResponseEntity.ok().body(patientsRepository.save(patients1));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patients> deleteById(@PathVariable Long id){
        return patientsRepository.findById(id)
                .map(patients -> {
                    patientsRepository.deleteById(patients.getPatients_id());
                    return ResponseEntity.ok().body(patients);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}