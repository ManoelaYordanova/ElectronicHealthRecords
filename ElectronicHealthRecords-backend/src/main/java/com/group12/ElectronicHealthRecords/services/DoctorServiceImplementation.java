package com.group12.ElectronicHealthRecords.services;

import lombok.RequiredArgsConstructor;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.repositories.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DoctorServiceImplementation implements DoctorService, UserDetailsService {

    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if(!doctor.isPresent()) {
            log.error("Doctor not found in the database!");
            throw new UsernameNotFoundException("Doctor not found in the database!");
        } else {
            log.info("Doctor found in the database: {}", email);
        }
        return new org.springframework.security.core.userdetails.User(doctor.get().getEmail(), doctor.get().getPassword(), new ArrayList<>());
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        log.info("Saving new doctor to the database.");
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> getDoctorByEmail(String email) {
        return doctorRepository.findByEmail(email);
    }

    @Override
    public Optional<Doctor> getDoctorById(String egn) {
        return doctorRepository.findById(egn);
    }

    @Override
    public List<Doctor> getDoctors() {
        log.info("Fetching all doctors.");
        return doctorRepository.findAll();
    }
}
