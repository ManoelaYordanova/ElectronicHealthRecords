package com.group12.ElectronicHealthRecords.services;

import com.group12.ElectronicHealthRecords.beans.CalendarRequest;
import com.group12.ElectronicHealthRecords.entities.Calendar;
import com.group12.ElectronicHealthRecords.entities.Doctor;
import com.group12.ElectronicHealthRecords.entities.Patient;
import com.group12.ElectronicHealthRecords.repositories.CalendarRepository;
import com.group12.ElectronicHealthRecords.repositories.DoctorRepository;
import com.group12.ElectronicHealthRecords.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public ResponseEntity<?> getAppointmentById(Long id) {
        Optional<Calendar> calendar = calendarRepository.findById(id);
        return calendar.isPresent() ? ResponseEntity.ok().body(calendar.get()) : ResponseEntity.notFound().build();
    }

    public List<Calendar> getAppointments() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Doctor> doctor = doctorRepository.findByEmail(auth.getPrincipal().toString());

        return calendarRepository.findAllDoctorApointments(doctor.get().getEgn());
    }

    public List<Calendar> findAppointmentsInPeriod(LocalDateTime start, LocalDateTime end) {
        List<Calendar> appointments = this.getAppointments();

        List<Calendar> filteredAppointments = appointments.stream().filter(c ->
                c.getDate().isAfter(start) && c.getDate().isBefore(end)).collect(Collectors.toList());
        return filteredAppointments;
    }

    public boolean hasOverlappingAppointments(Calendar calendar) {
        List<Calendar> appointments = this.getAppointments();

        LocalDateTime start = calendar.getDate();
        LocalDateTime end = calendar.getDate().plusHours(calendar.getDuration());

        List<Calendar> filteredAppointments = appointments.stream().filter(c -> {
            LocalDateTime currentStart = c.getDate();
            LocalDateTime currentEnd = c.getDate().plusMinutes(c.getDuration());
            return start.isBefore(currentEnd) && end.isAfter(currentStart);
        }).collect(Collectors.toList());

        return filteredAppointments.size() != 0;
    }

    public ResponseEntity<?> createAppointment(CalendarRequest calendarRequest) {
        Map<String, String> response = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Doctor> doctor = doctorRepository.findByEmail(auth.getPrincipal().toString());

        Optional<Patient> patient = patientRepository.findByEgn(calendarRequest.getPatientEgn());
        if (!patient.isPresent()) {
            response.put("error_message", "No patient found with given EGN");
            return ResponseEntity.badRequest().body(response);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(calendarRequest.getDate(), dateTimeFormatter);

        Calendar calendar = new Calendar();
        calendar.setName(calendarRequest.getName());
        calendar.setDescription(calendarRequest.getDescription());
        calendar.setDoctor(doctor.get());
        calendar.setPatient(patient.get());
        calendar.setDate(dateTime);
        calendar.setDuration(calendarRequest.getDuration());

        if (this.hasOverlappingAppointments(calendar)) {
            response.put("error_message", "Appointments already exist in the given period");
            return ResponseEntity.badRequest().body(response);
        }

        calendarRepository.save(calendar);
        return ResponseEntity.ok().body(calendar);
    }

    public void deleteAppointment(Long id) {
        calendarRepository.findById(id).ifPresent(appointment -> calendarRepository.delete(appointment));
    }
}
