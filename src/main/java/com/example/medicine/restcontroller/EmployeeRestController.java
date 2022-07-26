package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.repository.EmployeeRepository;
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

    private EmployeeRepository repository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(employee -> ResponseEntity.ok().body(employee))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return ResponseEntity.ok(service.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> resave(@PathVariable Long id, @RequestBody Employee employee){
        return repository.findById(id)
                .map(employee1 -> ResponseEntity.ok().body(service.save(employee)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> reSave(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return repository.findById(id)
                .map(employee1 -> {
                    employee1.setEmployee_name(employee.getEmployee_name());
                    return ResponseEntity.ok().body(service.save(employee1));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(employee -> {
                    service.delete(id);
                    return ResponseEntity.ok().body(employee);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}