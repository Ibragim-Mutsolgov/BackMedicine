package com.example.medicine.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Work {

    @Id
    @Column(name = "work_id", nullable = false)
    private Long work_id;

    @Temporal(TemporalType.TIME)
    private Date start_time;

    @Temporal(TemporalType.TIME)
    private Date stop_time;

    @Temporal(TemporalType.TIME)
    private Date result_time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offices_offices_id", referencedColumnName = "offices_id")
    private Offices offices;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public Work(Date start_time, Date stop_time, Date result_time, Offices offices, Employee employee) {
        this.start_time = start_time;
        this.stop_time = stop_time;
        this.result_time = result_time;
        this.offices = offices;
        this.employee = employee;
    }
}
