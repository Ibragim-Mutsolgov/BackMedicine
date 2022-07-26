package com.example.medicine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employee_id;

    private String employee_name;

    public Employee(String employee_name) {
        this.employee_name = employee_name;
    }
}