package com.group12.ElectronicHealthRecords.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @Column(name = "egn", nullable = false, length = 10)
    private String egn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "allergies", nullable = false)
    private String allergies;

    @Column(name = "immunizationStatute", nullable = false)
    private String immunizationStatute;

    @Column(name = "bloodType", nullable = false)
    private Character bloodType;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "dateOfBirth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "illness")
    private String illness;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private List<Examination> examinations;

    @OneToMany(mappedBy = "patient", orphanRemoval = true)
    private List<Calendar> calendars;

    public Patient(String egn, String name, String email, String allergies, String immunizationStatute,
                   Character bloodType, Date dateOfBirth, String laboratoryResult, String illness) {
    }
}

