package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.DoctorRequest;
import com.group12.ElectronicHealthRecords.entities.AccessCode;
import com.group12.ElectronicHealthRecords.repositories.AccessCodeRepository;
import lombok.RequiredArgsConstructor;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final PasswordEncoder passwordEncoder;
    private final AccessCodeRepository accessCodeRepository;

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>>getDoctors() {
        return  ResponseEntity.ok().body(doctorService.getDoctors());
    }

    @PostMapping("/doctor/save")
    public ResponseEntity<?>saveDoctor(@RequestBody DoctorRequest doctorRequest) {
        Map<String, String> response = new HashMap<>();
        Optional<AccessCode> accessCode = accessCodeRepository.findById(doctorRequest.getAccessCode());
        if(!accessCode.isPresent() || accessCode.get().getUsed()) {
            response.put("error_message", "Invalid access code!");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Doctor> doctor;
        doctor = doctorService.getDoctorByEmail(doctorRequest.getEmail());
        if (doctor.isPresent()) {
            response.put("error_message", "User with this email already exists!");
            return ResponseEntity.badRequest().body(response);
        }

        doctor = doctorService.getDoctorById(doctorRequest.getEgn());
        if (doctor.isPresent()) {
            response.put("error_message", "User with this EGN already exists!");
            return ResponseEntity.badRequest().body(response);
        }

        accessCode.get().setUsed(true);

        doctorRequest.setPassword(passwordEncoder.encode(doctorRequest.getPassword()));
        Doctor newDoctor = new Doctor(
                doctorRequest.getEgn(),
                doctorRequest.getName(),
                doctorRequest.getEmail(),
                doctorRequest.getPassword(),
                doctorRequest.getSpecialization(),
                new ArrayList<>(),
                new ArrayList<>()
        );
        return  ResponseEntity.ok().body(doctorService.saveDoctor(newDoctor));
    }
}
