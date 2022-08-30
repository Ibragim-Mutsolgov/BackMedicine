package com.example.medicine.restcontroller;

import com.example.medicine.model.Employee;
import com.example.medicine.model.People;
import com.example.medicine.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Embeddable;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/people",
        consumes = "application/json", produces = "application/json")
public class PeopleRestController {

    private PeopleService service;

    @GetMapping
    public ResponseEntity<List<People>> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/emp")
    public ResponseEntity<People> findByEmployeeId(@RequestBody Employee employee) {
        return service.findByEmployeeId(employee);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<People> save(@RequestBody People people){
        return service.save(people);
    }

    @PutMapping("/{id}")
    public ResponseEntity<People> putSave(@PathVariable Long id, @RequestBody People people){
        return service.putSave(id, people);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<People> patchSave(@PathVariable Long id, @RequestBody People people){
        return service.patchSave(id, people);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<People> deleteById(@PathVariable Long id){
        return service.delete(id);
    }
}