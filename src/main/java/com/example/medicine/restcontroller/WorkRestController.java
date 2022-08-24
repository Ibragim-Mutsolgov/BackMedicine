package com.example.medicine.restcontroller;

import com.example.medicine.model.Work;
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

    @GetMapping
    public ResponseEntity<List<Work>> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Work> save(@RequestBody Work work){
        return service.save(work);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> putSave(@PathVariable Long id, @RequestBody Work work){
        return service.putSave(id, work);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Work> patchSave(@PathVariable Long id, @RequestBody Work work){
        return service.patchSave(id, work);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Work> deleteById(@PathVariable Long id){
        return service.delete(id);
    }
}
