package com.sunrise.dental.service;

import java.util.List;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Patient;

public class ReportService {

    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;

    public ReportService() {
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
    }

    public List<Appointment> generateAppointmentReport() {

        return appointmentDAO.findAll();
    }

    public List<Patient> generatePatientReport() {

        return patientDAO.findAll();
    }
}