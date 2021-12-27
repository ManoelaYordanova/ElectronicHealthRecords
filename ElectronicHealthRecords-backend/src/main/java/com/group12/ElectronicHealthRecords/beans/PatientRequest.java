package com.group12.ElectronicHealthRecords.beans;

import lombok.Data;
import java.util.Date;

@Data
public class PatientRequest {

    private String egn;
    private String name;
    private String email;
    private String allergies;
    private String immunizationStatute;
    private String bloodType;
    private Integer weight;
    private Date dateOfBirth;
    private String illness;

}
