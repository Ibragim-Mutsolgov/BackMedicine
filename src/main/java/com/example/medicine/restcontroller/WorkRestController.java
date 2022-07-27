package com.example.medicine.restcontroller;

import com.example.medicine.domain.Employee;
import com.example.medicine.domain.Offices;
import com.example.medicine.domain.Work;
import com.example.medicine.repository.WorkRepository;
import com.example.medicine.service.WorkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/work",
        consumes = "application/json")
public class WorkRestController {

    private WorkService service;

    private WorkRepository repository;

    private JmsTemplate jmsTemplate;

    @GetMapping
    public ResponseEntity<List<Work>> findAll(){
        List<Work> list = service.findAll();
        jmsTemplate.convertAndSend("workFindAll", true);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(work -> {
                    jmsTemplate.convertAndSend("workFindById", work);
                    return ResponseEntity.ok().body(work);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Work> save(@RequestBody Work work){
        Work workSave = service.save(work);
        jmsTemplate.convertAndSend("workSave", workSave);
        return ResponseEntity.ok(workSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> resave(@PathVariable Long id, @RequestBody Work work){
        return repository.findById(id)
                .map(work1 -> {
                    work1 = work;
                    service.save(work1);
                    jmsTemplate.convertAndSend("workPutSave", work1);
                    return ResponseEntity.ok().body(work1);
                })
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
                    service.save(work1);
                    jmsTemplate.convertAndSend("workPatchListener", work1);
                    return ResponseEntity.ok().body(work1);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Work> deleteById(@PathVariable Long id){
        return repository.findById(id)
                .map(work -> {
                    service.delete(work.getWork_id());
                    jmsTemplate.convertAndSend("workDelete", work);
                    return ResponseEntity.ok().body(work);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
