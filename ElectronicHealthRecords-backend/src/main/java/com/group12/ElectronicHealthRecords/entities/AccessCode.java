package com.group12.ElectronicHealthRecords.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="access_code")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessCode {

    @Id
    @Column(name = "access_code", nullable = false)
    private String accessCode;

    @Column(name = "used", nullable = false)
    private Boolean used = false;
}
