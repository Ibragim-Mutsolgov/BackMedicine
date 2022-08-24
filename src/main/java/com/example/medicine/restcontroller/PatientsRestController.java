package com.example.medicine.restcontroller;

import com.example.medicine.model.Patients;
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

    @GetMapping
    public ResponseEntity<List<Patients>> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patients> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patients> save(@RequestBody Patients patients){
        return service.save(patients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patients> putSave(@PathVariable Long id, @RequestBody Patients patients){
        return service.putSave(id, patients);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patients> patchSave(@PathVariable Long id, @RequestBody Patients patients){
        return service.patchSave(id, patients);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Patients> deleteById(@PathVariable Long id){
        return service.delete(id);
    }
}