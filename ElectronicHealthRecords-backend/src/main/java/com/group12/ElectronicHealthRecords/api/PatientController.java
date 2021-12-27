package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.PatientRequest;
import com.group12.ElectronicHealthRecords.repositories.PatientRepository;
import com.group12.ElectronicHealthRecords.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;
    PatientRepository patientRepository;

    @GetMapping("/search/patient/name")
    public ResponseEntity<?> getPatientsByName(@RequestParam String name){
        return patientService.getPatientsByName(name);
    }

    @GetMapping("/search/patient/egn")
    public ResponseEntity<?> getPatientsByEgn(@RequestParam String egn) {
        return patientService.getPatientsByEgn(egn);
    }

    @PostMapping("/patient/save")
    public ResponseEntity<?> savePatient(@RequestBody PatientRequest patientRequest) {
        return patientService.savePatient(patientRequest);
    }

    @PutMapping("/patient/update")
    public ResponseEntity<?> updatePatient(@RequestBody PatientRequest patientRequest) {
        return patientService.updatePatient(patientRequest);
    }
}
