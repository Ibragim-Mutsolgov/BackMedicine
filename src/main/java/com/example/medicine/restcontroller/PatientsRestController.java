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
@RequestMapping(path = "/patients",
        consumes = "application/json")
public class PatientsRestController {

    private PatientsService service;

    private PatientsRepository repository;

    @GetMapping
    public ResponseEntity<List<Patients>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(patients -> ResponseEntity.ok().body(patients))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patients> save(@RequestBody Patients patients){
        return ResponseEntity.ok(service.save(patients));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patients> resave(@PathVariable Long id, @RequestBody Patients patients){
        return repository.findById(id)
                .map(patients1 -> ResponseEntity.ok().body(service.save(patients)))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patients> reSave(@PathVariable Long id, @RequestBody Patients patients){
        return repository.findById(id)
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
                    return ResponseEntity.ok().body(service.save(patients1));
                })
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Patients> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(patients -> {
                    service.delete(patients.getPatients_id());
                    return ResponseEntity.ok().body(patients);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}