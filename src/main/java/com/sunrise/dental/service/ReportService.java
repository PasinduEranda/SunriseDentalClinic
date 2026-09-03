package com.sunrise.dental.service;

import java.util.List;

import com.sunrise.dental.dao.AppointmentDAO;
import com.sunrise.dental.dao.BillingDAO;
import com.sunrise.dental.dao.PatientDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.model.Patient;

public class ReportService {

    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;
    private BillingDAO billingDAO;

    public ReportService() {
        appointmentDAO = new AppointmentDAO();
        patientDAO = new PatientDAO();
        billingDAO = new BillingDAO();
    }

    public List<Appointment> generateAppointmentReport() {
        return appointmentDAO.findAll();
    }

    public List<Patient> generatePatientReport() {
        return patientDAO.findAll();
    }

    public List<Bill> generateBillingReport() {
        return billingDAO.findAll();
    }
}