package com.example.medicine.restcontroller;

import com.example.medicine.model.Offices;
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

    @GetMapping
    public ResponseEntity<List<Offices>> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offices> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Offices> save(@RequestBody Offices offices){
        return service.save(offices);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Offices> putSave(@PathVariable Long id, @RequestBody Offices offices){
        return service.putSave(id, offices);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Offices> patchSave(@PathVariable Long id, @RequestBody Offices offices){
        return service.patchSave(id, offices);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Offices> deleteById(@PathVariable Long id){
        return service.delete(id);
    }
}
