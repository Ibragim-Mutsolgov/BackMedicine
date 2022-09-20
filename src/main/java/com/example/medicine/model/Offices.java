package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Offices extends RepresentationModel<Offices> {

    @Id
    @Column(name = "offices_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long offices_id;

    private String number_offices;

    public Offices(String number_offices) {
        this.number_offices = number_offices;
    }
}
