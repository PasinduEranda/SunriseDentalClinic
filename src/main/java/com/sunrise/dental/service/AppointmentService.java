package com.sunrise.dental.service;

import com.sunrise.dental.util.ValidationUtil;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.dao.DentistDAO;
import com.sunrise.dental.dao.TreatmentDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Treatment;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private DentistDAO dentistDAO;
    private TreatmentDAO treatmentDAO;

    public AppointmentService() {

        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
        dentistDAO = new DentistDAO();
        treatmentDAO = new TreatmentDAO();
    }

    public boolean registerAppointment(Appointment appointment) {

        if (appointment == null) {
            return false;
        }

        Patient patient = appointment.getPatient();
        Dentist dentist = appointment.getDentist();
        Treatment treatment = appointment.getTreatment();

        if (patient == null || dentist == null || treatment == null) {
            return false;
        }

        if (!ValidationUtil.isValidName(patient.getName())) {

            return false;

        }

        if (!ValidationUtil.isValidContactNumber(patient.getContactNumber())) {

            return false;

        }

        if (appointment.getAppointmentDate() == null
                || appointment.getAppointmentTime() == null) {
            return false;
        }

        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            return false;
        }

        if (dentistDAO.findById(dentist.getDentistId()) == null) {
            return false;
        }

        if (treatmentDAO.findById(treatment.getTreatmentId()) == null) {
            return false;
        }

        if (patient.getPatientId() <= 0) {

            if (!patientDAO.save(patient)) {
                return false;
            }
        }

        boolean available = appointmentDAO.isDentistAvailable(
                dentist.getDentistId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime()
        );

        if (!available) {
            return false;
        }

        appointment.setStatus("Scheduled");

        return appointmentDAO.save(appointment);
    }

    public Appointment searchAppointment(int appointmentNo) {

        if (appointmentNo <= 0) {
            return null;
        }

        return appointmentDAO.findByAppointmentNo(appointmentNo);
    }

    public boolean checkAvailability(
            int dentistId,
            LocalDate date,
            LocalTime time) {

        if (dentistId <= 0 || date == null || time == null) {
            return false;
        }

        return appointmentDAO.isDentistAvailable(
                dentistId,
                date,
                time
        );
    }
}