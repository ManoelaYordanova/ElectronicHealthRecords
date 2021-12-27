package com.group12.ElectronicHealthRecords.beans;

import lombok.Data;

@Data
public class CalendarRequest {

    private String name;
    private String date;
    private String description;
    private Integer duration;
    private String patientEgn;
}
