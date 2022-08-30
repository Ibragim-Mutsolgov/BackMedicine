package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patients {

    @Id
    @Column(name = "patients_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patients_id;

    private String patients_number_ambulant_card;

    private String patients_policy;

    private String patients_type_policy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patients_patients_id", referencedColumnName = "employee_id")
    private Employee employee;

    public Patients(String patients_number_ambulant_card, String patients_policy, String patients_type_policy) {
        this.patients_number_ambulant_card = patients_number_ambulant_card;
        this.patients_policy = patients_policy;
        this.patients_type_policy = patients_type_policy;
    }
}