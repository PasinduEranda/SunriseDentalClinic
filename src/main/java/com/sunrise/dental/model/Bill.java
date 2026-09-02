package com.sunrise.dental.model;

public class Bill {

    private int billId;
    private double treatmentCost;
    private double consultationFee;
    private double totalAmount;

    private Appointment appointment;

    public Bill() {
    }

    public Bill(int billId, double treatmentCost,
                double consultationFee, Appointment appointment) {

        this.billId = billId;
        this.treatmentCost = treatmentCost;
        this.consultationFee = consultationFee;
        this.appointment = appointment;

        calculateTotal();
    }

    public void calculateTotal() {
        this.totalAmount = treatmentCost + consultationFee;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getTreatmentCost() {
        return treatmentCost;
    }

    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
        calculateTotal();
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
        calculateTotal();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}