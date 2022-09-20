package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends RepresentationModel<Employee> {

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employee_id;

    private UUID peopleId;

    public Employee(UUID peopleId) {
        this.peopleId = peopleId;
    }
}