package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Patients;
import com.example.medicine.domain.People;
import com.example.medicine.repository.PeopleRepository;
import com.example.medicine.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/people",
        consumes = "application/json")
public class PeopleRestController {

    private PeopleService service;

    private PeopleRepository repository;

    private JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<List<People>> findAll(){
        List<People> list = service.findAll();
        jmsTemplate.convertAndSend("peopleFindAll", true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(people -> {
                    jmsTemplate.convertAndSend("peopleFindById", people);
                    return ResponseEntity.ok().body(people);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<People> save(@RequestBody People people){
        People peopleSave = service.save(people);
        jmsTemplate.convertAndSend("peopleSave", peopleSave);
        return ResponseEntity.ok(peopleSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<People> resave(@PathVariable Long id, @RequestBody People people){
        return repository.findById(id)
                .map(people1 -> {
                    people1 = people;
                    service.save(people1);
                    jmsTemplate.convertAndSend("peoplePutSave", people1);
                    return ResponseEntity.ok().body(people1);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<People> reSave(
            @PathVariable Long id,
            @RequestBody People peo){
        return repository.findById(id)
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
                    service.save(people);
                    jmsTemplate.convertAndSend("peoplePatchListener", people);
                    return ResponseEntity.ok().body(people);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<People> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(people -> {
                    service.delete(people.getId());
                    jmsTemplate.convertAndSend("peopleDelete", people);
                    return ResponseEntity.ok().body(people);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}