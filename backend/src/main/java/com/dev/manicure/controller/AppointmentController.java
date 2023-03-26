package com.dev.manicure.controller;

import com.dev.manicure.entity.Appointment;
import com.dev.manicure.entity.User;
import com.dev.manicure.repository.AppointmentRepository;
import com.dev.manicure.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/appointment/create")
    public ResponseEntity <Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    @PutMapping(value = "/appointment/{id}")
    public ResponseEntity<Appointment>  updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        appointment = appointmentService.updateAppointment(id,appointment);
        return ResponseEntity.created(URI.create("/appointment" + appointment.getId())).body(appointment);
    }

    @GetMapping("/appointment/list")
    public List<Appointment> getAppointments(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return appointmentService.getAppointments(start, end);
    }

    @DeleteMapping(value = "/appointment/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id){
        return appointmentService.deleteUser(id);
    }
}
