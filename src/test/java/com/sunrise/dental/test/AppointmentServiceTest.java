package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.service.AppointmentService;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

public class AppointmentServiceTest {
	
	@Test

    void testNullAppointmentIsRejected() {

        AppointmentService appointmentService = new AppointmentService();

        boolean result = appointmentService.registerAppointment(null);

        assertFalse(result);

    }

	@Test
	void testMissingPatientNameIsRejected() {

	    AppointmentService appointmentService = new AppointmentService();

	    Patient patient = new Patient();
	    patient.setName("");
	    patient.setContactNumber("0771234567");

	    Dentist dentist = new Dentist();
	    dentist.setDentistId(1);

	    Treatment treatment = new Treatment();
	    treatment.setTreatmentId(1);

	    Appointment appointment = new Appointment();
	    appointment.setPatient(patient);
	    appointment.setDentist(dentist);
	    appointment.setTreatment(treatment);
	    appointment.setAppointmentDate(java.time.LocalDate.now().plusDays(1));
	    appointment.setAppointmentTime(java.time.LocalTime.of(10, 0));

	    boolean result = appointmentService.registerAppointment(appointment);

	    assertFalse(result);
	}
}