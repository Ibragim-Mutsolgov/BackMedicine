package com.example.medicine.restcontroller;

import com.example.medicine.model.DoctorsPosition;
import com.example.medicine.service.DoctorsPositionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/doctors-position",
        consumes = "application/json")
public class DoctorsPositionRestController {

    private DoctorsPositionService service;

    @GetMapping
    public ResponseEntity<List<DoctorsPosition>> findAll(){
        return service.findAll();
    }
}
