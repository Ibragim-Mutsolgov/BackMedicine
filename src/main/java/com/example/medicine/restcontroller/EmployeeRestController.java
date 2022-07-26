package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.repository.EmployeeRepository;
import com.example.medicine.serviceimpl.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        return employeeRepository.findById(id)
                .map(employee1 -> {
                    Employee employee = employeeService.findById(id);
                    return ResponseEntity.ok().body(employee);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> resave(@PathVariable Long id, @RequestBody Employee employee){
        return employeeRepository.findById(id)
                .map(employee1 -> ResponseEntity.ok().body(employeeService.save(employee1)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> reSave(
            @PathVariable Long id,
            @RequestBody Employee employee) {
        return employeeRepository.findById(id)
                .map(employee1 -> {
                    employee1.setEmployee_name(employee.getEmployee_name());
                    return ResponseEntity.ok().body(employeeService.save(employee1));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable Long id){
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeService.delete(id);
                    return ResponseEntity.ok().body(employee);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}