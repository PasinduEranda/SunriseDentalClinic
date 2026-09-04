package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.service.AppointmentService;

public class DentistAvailabilityTest {

    @Test
    void testBookedDentistSlotIsNotAvailable() {

        AppointmentService appointmentService = new AppointmentService();

        boolean available = appointmentService.checkAvailability(
                2,
                LocalDate.of(2026, 9, 20),
                LocalTime.of(9, 17)
        );

        assertFalse(available);
    }
    
    @Test
    void testAvailableDentistSlotIsAvailable() {

        AppointmentService appointmentService = new AppointmentService();

        boolean available = appointmentService.checkAvailability(
                2,
                LocalDate.of(2026, 9, 20),
                LocalTime.of(14, 00)
        );

        assertTrue(available);
    }
}