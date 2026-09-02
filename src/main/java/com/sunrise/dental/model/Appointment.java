package com.sunrise.dental.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private int appointmentNo;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;

    private Patient patient;
    private Dentist dentist;
    private Treatment treatment;

    public Appointment() {
    }

    public Appointment(int appointmentNo, LocalDate appointmentDate,
                       LocalTime appointmentTime, String status,
                       Patient patient, Dentist dentist,
                       Treatment treatment) {

        this.appointmentNo = appointmentNo;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.patient = patient;
        this.dentist = dentist;
        this.treatment = treatment;
    }

    public int getAppointmentNo() {
        return appointmentNo;
    }

    public void setAppointmentNo(int appointmentNo) {
        this.appointmentNo = appointmentNo;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
}