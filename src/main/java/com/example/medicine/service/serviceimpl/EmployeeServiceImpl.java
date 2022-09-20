package com.example.medicine.service.serviceimpl;

import com.example.medicine.model.Employee;
import com.example.medicine.repository.EmployeeRepository;
import com.example.medicine.restcontroller.EmployeeRestController;
import com.example.medicine.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok().body(employeeRepository.findAll());
    }

    @Override
    public ResponseEntity<Employee> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    addHateos(employee);
                    return ResponseEntity.ok().body(employee);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> save(Employee employee) {
        employee = employeeRepository.save(employee);
        addHateos(employee);
        return ResponseEntity.ok().body(employee);
    }

    @Override
    public ResponseEntity<Employee> putSave(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(employeeSave -> {
                    employeeSave.setEmployee_id(id);
                    employeeSave.setPeopleId(employee.getPeopleId());
                    addHateos(employeeSave);
                    return ResponseEntity.ok().body(employeeRepository.save(employeeSave));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> patchSave(Long id, Employee employee) {
        return employeeRepository.findById(id)
                .map(employeeSave -> {
                    employeeSave.setPeopleId(employee.getPeopleId());
                    addHateos(employeeSave);
                    return ResponseEntity.ok().body(employeeRepository.save(employeeSave));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Employee> delete(Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    addHateos(employee);
                    employeeRepository.deleteById(employee.getEmployee_id());
                    return ResponseEntity.ok().body(employee);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    private Employee addHateos(Employee employee) {
        employee.add(
                linkTo(methodOn(EmployeeRestController.class)
                        .findById(employee.getEmployee_id())).withRel("findById"),
                linkTo(methodOn(EmployeeRestController.class)
                        .save(employee)).withRel("save"),
                linkTo(methodOn(EmployeeRestController.class)
                        .putSave(employee.getEmployee_id(), employee)).withRel("putSave"),
                linkTo(methodOn(EmployeeRestController.class)
                        .patchSave(employee.getEmployee_id(), employee)).withRel("patchSave"),
                linkTo(methodOn(EmployeeRestController.class)
                        .deleteById(employee.getEmployee_id())).withRel("deleteById")
        );
        return employee;
    }
}