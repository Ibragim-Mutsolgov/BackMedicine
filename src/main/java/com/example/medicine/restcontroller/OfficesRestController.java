package com.example.medicine.restcontroller;

import com.example.medicine.domain.Offices;
import com.example.medicine.repository.OfficesRepository;
import com.example.medicine.service.OfficesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/offices",
        consumes = "application/json")
public class OfficesRestController {

    private OfficesService service;

    private OfficesRepository repository;

    @GetMapping
    public ResponseEntity<List<Offices>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offices> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(offices -> ResponseEntity.ok().body(offices))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Offices> save(@RequestBody Offices offices){
        return ResponseEntity.ok(service.save(offices));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offices> resave(@PathVariable Long id,
                                          @RequestBody Offices offices){
        return repository.findById(id)
                .map(offices1 -> ResponseEntity.ok(service.save(offices)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Offices> reSave(@PathVariable Long id,
                                          @RequestBody Offices offices){
        return repository.findById(id)
                .map(offices1 -> {
                    if(offices.getNumber_offices() != null){
                        offices1.setNumber_offices(offices.getNumber_offices());
                    }
                    return ResponseEntity.ok().body(service.save(offices1));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Offices> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(offices -> {
                    service.delete(offices.getOffices_id());
                    return ResponseEntity.ok().body(offices);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
