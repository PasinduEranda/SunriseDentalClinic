package com.sunrise.dental.model;

public class Bill {

    private int billId;
    private double treatmentCost;
    private double consultationFee;
    private double totalAmount;

    private Appointment appointment;

    // Default constructor
    public Bill() {
    }

    // Parameterized constructor
    public Bill(int billId,
                double treatmentCost,
                double consultationFee,
                Appointment appointment) {

        this.billId = billId;
        this.treatmentCost = treatmentCost;
        this.consultationFee = consultationFee;
        this.appointment = appointment;

        calculateTotal();
    }

    // Calculate total bill amount
    public void calculateTotal() {
        this.totalAmount = treatmentCost + consultationFee;
    }

    // Get Bill ID
    public int getBillId() {
        return billId;
    }

    // Set Bill ID
    public void setBillId(int billId) {
        this.billId = billId;
    }

    // Get Treatment Cost
    public double getTreatmentCost() {
        return treatmentCost;
    }

    // Set Treatment Cost
    public void setTreatmentCost(double treatmentCost) {
        this.treatmentCost = treatmentCost;
        calculateTotal();
    }

    // Get Consultation Fee
    public double getConsultationFee() {
        return consultationFee;
    }

    // Set Consultation Fee
    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
        calculateTotal();
    }

    // Get Total Amount
    public double getTotalAmount() {
        return totalAmount;
    }

    // Set Total Amount
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Get Appointment
    public Appointment getAppointment() {
        return appointment;
    }

    // Set Appointment
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}