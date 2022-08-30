package com.example.medicine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doctors_work")
public class DoctorsPosition {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "parent_id", nullable = true)
    private Integer parent_id;

    @Column(name = "work_position", nullable = false)
    private String work_position;

    @Column(name = "med", nullable = true)
    private Integer med;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_end", nullable = true)
    private Date date_end;

    @Column(name = "form_30", nullable = true)
    private Integer form_30;

    @Column(name = "need_cert", nullable = true)
    private Integer need_cert;

    @Column(name = "educ", nullable = true)
    private Integer educ;

    @Column(name = "zs", nullable = false)
    private int zs;

    @Column(name = "show_staff_list", nullable = true)
    private Integer show_staff_list;

    @Column(name = "federal_code", nullable = true)
    private Integer federal_code;

    public DoctorsPosition(Integer parent_id, String work_position, Integer med, Date date_end, Integer form_30, Integer need_cert, Integer educ, int zs, Integer show_staff_list, Integer federal_code) {
        this.parent_id = parent_id;
        this.work_position = work_position;
        this.med = med;
        this.date_end = date_end;
        this.form_30 = form_30;
        this.need_cert = need_cert;
        this.educ = educ;
        this.zs = zs;
        this.show_staff_list = show_staff_list;
        this.federal_code = federal_code;
    }
}
