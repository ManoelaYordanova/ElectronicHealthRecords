package com.group12.ElectronicHealthRecords.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @Column(name = "egn", nullable = false, length = 10)
    private String egn;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Examination> examinations;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private List<Calendar> calendars;
}
