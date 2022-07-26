package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Offices;
import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import com.example.medicine.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/work",
        consumes = "application/json")
public class WorkRestController {

    private WorkService service;

    private WorkRepository repository;

    @GetMapping
    public ResponseEntity<List<Work>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(work -> ResponseEntity.ok().body(work))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Work> save(@RequestBody Work work){
        return ResponseEntity.ok(service.save(work));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> resave(@PathVariable Long id, @RequestBody Work work){
        return repository.findById(id)
                .map(work1 -> ResponseEntity.ok(service.save(work)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Work> reSave(@PathVariable Long id, @RequestBody Work work){
        return repository.findById(id)
                .map(work1 -> {
                    if(work.getStart_time() != null){
                        work1.setStart_time(work.getStart_time());
                    }
                    if(work.getStop_time() != null){
                        work1.setStop_time(work.getStop_time());
                    }
                    if(work.getResult_time() != null){
                        work1.setResult_time(work.getResult_time());
                    }
                    if(work.getOffices() != null){
                        Offices offices = new Offices();
                        if(work.getOffices().getNumber_offices() != null){
                            offices.setNumber_offices(
                                    work.getOffices().getNumber_offices());
                        }
                        work1.setOffices(offices);
                    }
                    if(work.getEmployee() != null){
                        Employee employee = new Employee();
                        if(work.getEmployee().getEmployee_name() != null){
                            employee.setEmployee_name(
                                    work.getEmployee().getEmployee_name());
                        }
                        work1.setEmployee(employee);
                    }
                    return ResponseEntity.ok().body(service.save(work1));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Work> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(work -> {
                    service.delete(work.getWork_id());
                    return ResponseEntity.ok().body(work);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
