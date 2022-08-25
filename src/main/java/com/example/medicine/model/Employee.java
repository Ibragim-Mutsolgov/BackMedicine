package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @Column(name = "employee_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employee_id;

    @JoinColumn(name="position_id", referencedColumnName="work_position")
    private String employee_name;

    public Employee(String employee_name) {
        this.employee_name = employee_name;
    }
}