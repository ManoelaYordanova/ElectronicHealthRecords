package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    Optional<Doctor> getDoctorByEmail(String email);
    Optional<Doctor> getDoctorById(String egn);
    List<Doctor> getDoctors();
}
