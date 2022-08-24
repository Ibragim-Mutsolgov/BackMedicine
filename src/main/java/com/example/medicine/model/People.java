package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class People {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //ФИО, дата рождения, пол, место рождения
    private String surname; // Фамилия

    private String name; // Имя

    private String patronymic; // Отчество

    @Temporal(TemporalType.DATE)
    private Date date; // Дата рождения

    private int gender; // Пол

    private String placeBirth; // Место рождения

    //Серия и номер паспорта, кем выдан и когда, код подразделения
    private Long passportSeries; // Паспорт - серия

    private Long passportNumber; // Паспорт - номер

    private String passportIssue; // Паспорт выдан

    @Temporal(TemporalType.DATE)
    private Date dateIssue; // Дата выдачи

    private String departmentCode; // Код подразделения

    //Регион, пункт, район, улица
    private String region; // Регион

    private String station; // Пункт

    private String locality; // Район

    private String street; // Улица

    //OneToOne
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patients_patients_id", referencedColumnName = "patients_id")
    private Patients patients;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    public People(String surname, String name, String patronymic, Date date, int gender, String placeBirth, Long passportSeries, Long passportNumber, String passportIssue, Date dateIssue, String departmentCode, String region, String station, String locality, String street, Patients patients, Employee employee) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date = date;
        this.gender = gender;
        this.placeBirth = placeBirth;
        this.passportSeries = passportSeries;
        this.passportNumber = passportNumber;
        this.passportIssue = passportIssue;
        this.dateIssue = dateIssue;
        this.departmentCode = departmentCode;
        this.region = region;
        this.station = station;
        this.locality = locality;
        this.street = street;
        this.patients = patients;
        this.employee = employee;
    }
}