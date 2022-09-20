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
public class Patients extends RepresentationModel<Patients> {

    @Id
    @Column(name = "patients_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patients_id;

    private UUID peopleId;

    public Patients(UUID peopleId) {
        this.peopleId = peopleId;
    }
}