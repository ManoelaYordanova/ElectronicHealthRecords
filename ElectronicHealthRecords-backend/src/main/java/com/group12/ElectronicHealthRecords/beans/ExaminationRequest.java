package com.group12.ElectronicHealthRecords.beans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExaminationRequest {

    private Long id;
    private String results;
    private Date date;
    private String patientEgn;
}
