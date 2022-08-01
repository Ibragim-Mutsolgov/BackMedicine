package com.example.medicine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Offices {

    @Id
    @Column(name = "offices_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long offices_id;

    private String number_offices;

    public Offices(String number_offices) {
        this.number_offices = number_offices;
    }
}
