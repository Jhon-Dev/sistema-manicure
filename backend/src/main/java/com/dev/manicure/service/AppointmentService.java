package com.dev.manicure.service;

import com.dev.manicure.entity.Appointment;
import com.dev.manicure.entity.User;
import com.dev.manicure.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }


    public Appointment updateAppointment(Long id, Appointment appointmentHolder) {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Appointment not found!"));
        appointment.setDateTime(appointmentHolder.getDateTime());
        appointment.setServiceType(appointmentHolder.getServiceType());
        appointment.setUser(appointmentHolder.getUser());
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointments(LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDateTimeBetween(start, end);
    }

    public ResponseEntity<User> deleteUser(Long id) {
        appointmentRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Appointment not found!"));
        appointmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
