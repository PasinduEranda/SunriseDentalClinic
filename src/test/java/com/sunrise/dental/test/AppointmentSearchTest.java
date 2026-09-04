package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.service.AppointmentService;

public class AppointmentSearchTest {

    @Test
    void testInvalidAppointmentNumberReturnsNull() {

        AppointmentService appointmentService = new AppointmentService();

        Appointment appointment =
                appointmentService.searchAppointment(999999);

        assertNull(appointment);
    }
    
    @Test
    void testValidAppointmentNumberReturnsAppointment() {

        AppointmentService appointmentService = new AppointmentService();

        Appointment appointment =
                appointmentService.searchAppointment(3);

        assertNotNull(appointment);
        assertEquals(3, appointment.getAppointmentNo());
    }
}