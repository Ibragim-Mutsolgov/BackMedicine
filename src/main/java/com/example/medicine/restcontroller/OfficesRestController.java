package com.example.medicine.restcontroller;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.service.OfficesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/offices",
        consumes = "application/json")
public class OfficesRestController {

    private OfficesService service;

    private OfficesRepository repository;

    private JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<List<Offices>> findAll(){
        List<Offices> list = service.findAll();
        jmsTemplate.convertAndSend("officesFindAll", true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offices> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(offices -> {
                    jmsTemplate.convertAndSend("officesFindById", offices);
                    return ResponseEntity.ok().body(offices);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Offices> save(@RequestBody Offices offices){
        Offices officesSave = service.save(offices);
        jmsTemplate.convertAndSend("officesSave", officesSave);
        return ResponseEntity.ok(officesSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offices> resave(@PathVariable Long id,
                                          @RequestBody Offices offices){
        return repository.findById(id)
                .map(offices1 -> {
                    offices1 = offices;
                    service.save(offices1);
                    jmsTemplate.convertAndSend("officesPutSave", offices1);
                    return ResponseEntity.ok().body(offices1);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Offices> reSave(@PathVariable Long id,
                                          @RequestBody Offices offices){
        return repository.findById(id)
                .map(offices1 -> {
                    offices1.setNumber_offices(offices.getNumber_offices());
                    service.save(offices1);
                    jmsTemplate.convertAndSend("officesPatchListener", offices1);
                    return ResponseEntity.ok().body(offices1);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Offices> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(offices -> {
                    service.delete(offices.getOffices_id());
                    jmsTemplate.convertAndSend("officesDelete", offices);
                    return ResponseEntity.ok().body(offices);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
