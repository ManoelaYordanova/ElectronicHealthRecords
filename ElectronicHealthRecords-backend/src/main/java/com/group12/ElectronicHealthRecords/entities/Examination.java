package com.group12.ElectronicHealthRecords.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examination")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "results", nullable = false)
    private String results;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "doctor_egn")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_egn")
    private Patient patient;

    public Examination(Long id, String results, Date date, String patientEgn) {
    }
}
