package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.CalendarRequest;
import com.group12.ElectronicHealthRecords.entities.Calendar;
import com.group12.ElectronicHealthRecords.services.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/appointments/all")
    public List<Calendar> findAllAppointments() {
        return calendarService.getAppointments();
    }

    @GetMapping("/appointment")
    public ResponseEntity<?> findAppointmentById(@RequestParam Long id) {
        return calendarService.getAppointmentById(id);
    }

    @GetMapping("/appointments/period")
    public List<Calendar> findAppointmentsInPeriod(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")  LocalDateTime end) {
        return calendarService.findAppointmentsInPeriod(start, end);
    }

    @PostMapping("/appointment/save")
    public ResponseEntity<?> createAppointment(@RequestBody CalendarRequest calendarRequest) {
        return calendarService.createAppointment(calendarRequest);
    }

    @DeleteMapping("/appointment/delete")
    public void deleteAppointment(@RequestParam Long id) {
        calendarService.deleteAppointment(id);
    }

}
