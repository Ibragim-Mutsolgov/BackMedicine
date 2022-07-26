package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Patients;
import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.serviceimpl.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class PeopleRestController {

    private PeopleService peopleService;
    private PeopleRepository peopleRepository;

    @GetMapping
    public ResponseEntity<List<People>> findAll(){
        return ResponseEntity.ok(peopleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> findById(@PathVariable Long id){
        return peopleRepository.findById(id)
                .map(people1 -> {
                    People people = peopleService.findById(id);
                    return ResponseEntity.ok().body(people);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<People> save(@RequestBody People people){
        return ResponseEntity.status(HttpStatus.CREATED).body(peopleService.save(people));
    }

    @PutMapping("/{id}")
    public ResponseEntity<People> resave(@PathVariable Long id, @RequestBody People people){
        return peopleRepository.findById(id)
                .map(people1 -> ResponseEntity.ok().body(peopleService.save(people)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<People> reSave(
            @PathVariable Long id,
            @RequestBody People peo){
        return peopleRepository.findById(id)
                .map(people -> {
                    if(peo.getSurname() != null){
                        people.setSurname(peo.getSurname());
                    }
                    if(peo.getName() != null){
                        people.setName(peo.getName());
                    }
                    if(peo.getPatronymic() != null){
                        people.setPatronymic(peo.getPatronymic());
                    }
                    if(peo.getDate() != null){
                        people.setDate(peo.getDate());
                    }
                    if(peo.getGender() != 0){
                        people.setGender(peo.getGender());
                    }
                    if(peo.getPlaceBirth() != null){
                        people.setPlaceBirth(peo.getPlaceBirth());
                    }
                    if(peo.getPassportSeries() != null){
                        people.setPassportSeries(peo.getPassportSeries());
                    }
                    if(peo.getPassportNumber() != null){
                        people.setPassportNumber(peo.getPassportNumber());
                    }
                    if(peo.getPassportIssue() != null){
                        people.setPassportIssue(peo.getPassportIssue());
                    }
                    if(peo.getDateIssue() != null){
                        people.setDateIssue(peo.getDateIssue());
                    }
                    if(peo.getDepartmentCode() != null){
                        people.setDepartmentCode(peo.getDepartmentCode());
                    }
                    if(peo.getRegion() != null){
                        people.setRegion(people.getRegion());
                    }
                    if(peo.getStation() != null){
                        people.setStation(peo.getStation());
                    }
                    if(peo.getLocality() != null){
                        people.setLocality(peo.getLocality());
                    }
                    if(peo.getStreet() != null){
                        people.setStreet(peo.getStreet());
                    }
                    if(peo.getEmployee() != null) {
                        Employee employee = new Employee();
                        if (peo.getEmployee().getEmployee_name() != null) {
                            employee.setEmployee_name(peo.getEmployee().getEmployee_name());
                            people.setEmployee(employee);
                        }
                    }
                    if(peo.getPatients() != null) {
                        Patients patients = new Patients();
                        if (peo.getPatients().getPatients_number_ambulant_card() != null) {
                            patients.setPatients_number_ambulant_card(peo.getPatients().getPatients_number_ambulant_card());
                        }
                        if (peo.getPatients().getPatients_policy() != null) {
                            patients.setPatients_policy(peo.getPatients().getPatients_policy());
                        }
                        if (peo.getPatients().getPatients_type_policy() != null) {
                            patients.setPatients_type_policy(peo.getPatients().getPatients_type_policy());
                        }
                        people.setPatients(patients);
                    }
                    return ResponseEntity.ok().body(peopleService.save(people));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<People> deleteById(@PathVariable Long id){
        return peopleRepository.findById(id)
                .map(people -> {
                    peopleService.delete(people.getId());
                    return ResponseEntity.ok().body(people);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}