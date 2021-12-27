package com.group12.ElectronicHealthRecords.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    private String accessCode;
    private String name;
    private String egn;
    private String email;
    private String password;
    private String specialization;

}
