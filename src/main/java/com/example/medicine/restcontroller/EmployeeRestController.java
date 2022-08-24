package com.example.medicine.restcontroller;

import com.example.medicine.model.Employee;
import com.example.medicine.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/employee",
        consumes = "application/json")
public class EmployeeRestController {

    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return service.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> putSave(@PathVariable Long id, @RequestBody Employee employee){
        return service.putSave(id, employee);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> patchSave(@PathVariable Long id, @RequestBody Employee employee) {
        return service.patchSave(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
        return service.delete(id);
    }
}