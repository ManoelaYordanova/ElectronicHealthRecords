package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.beans.ExaminationRequest;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.entities.Examination;
import com.group12.ElectronicHealthRecords.entities.Patient;
import com.group12.ElectronicHealthRecords.repositories.DoctorRepository;
import com.group12.ElectronicHealthRecords.repositories.ExaminationRepository;
import com.group12.ElectronicHealthRecords.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final PatientRepository patientRepository;
    private  final DoctorRepository doctorRepository;

    public ResponseEntity<?> createExamination(ExaminationRequest examinationRequest) {
        Optional<Patient> patient = patientRepository.findByEgn(examinationRequest.getPatientEgn());
        Map<String, String> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Doctor> doctor = doctorRepository.findByEmail(auth.getPrincipal().toString());

        if (!patient.isPresent()) {
            response.put("error_message", "No patient found with given EGN");
            return ResponseEntity.badRequest().body(response);
        }

        Examination newExamination = new Examination();
                newExamination.setResults(examinationRequest.getResults());
                newExamination.setDate(examinationRequest.getDate());
                newExamination.setPatient(patient.get());
                newExamination.setDoctor(doctor.get());

        examinationRepository.save(newExamination);
        return ResponseEntity.ok().body(newExamination);
    }

    public ResponseEntity<?> getPatientExaminationByEgn(String patientEgn) {
        List<Examination> examination = examinationRepository.findAllPatientExaminations(patientEgn);
        return ResponseEntity.ok().body(examination);
    }

    public ResponseEntity<?> updateExamination(ExaminationRequest examinationRequest){
        Optional<Patient> patient = patientRepository.findByEgn(examinationRequest.getPatientEgn());
        Map<String, String> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Doctor> doctor = doctorRepository.findByEmail(auth.getPrincipal().toString());

        if (!patient.isPresent()) {
            response.put("error_message", "No patient found with given EGN");
            return ResponseEntity.badRequest().body(response);
        }

        Examination newExamination = new Examination();
        newExamination.setId(examinationRequest.getId());
        newExamination.setResults(examinationRequest.getResults());
        newExamination.setDate(examinationRequest.getDate());
        newExamination.setPatient(patient.get());
        newExamination.setDoctor(doctor.get());

        examinationRepository.save(newExamination);
        return ResponseEntity.ok().body(newExamination);
    }
}
