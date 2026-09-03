package com.sunrise.dental.service;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;

public class BillingService {

    private static final double CONSULTATION_FEE = 1000.00;

    public BillingService() {
    }

    public Bill calculateBill(Appointment appointment) {

        if (appointment == null) {
            return null;
        }

        if (appointment.getTreatment() == null) {
            return null;
        }

        double treatmentCost = appointment.getTreatment().getFee();

        Bill bill = new Bill(
                0,
                treatmentCost,
                CONSULTATION_FEE,
                appointment
        );

        bill.calculateTotal();

        return bill;
    }

    public void printReceipt(Bill bill) {

        if (bill == null) {
            return;
        }

        System.out.println("================================");
        System.out.println("      SUNRISE DENTAL CLINIC     ");
        System.out.println("================================");

        System.out.println(
                "Appointment No : "
                + bill.getAppointment().getAppointmentNo()
        );

        System.out.println(
                "Patient        : "
                + bill.getAppointment().getPatient().getName()
        );

        System.out.println(
                "Dentist        : "
                + bill.getAppointment().getDentist().getName()
        );

        System.out.println(
                "Treatment      : "
                + bill.getAppointment().getTreatment().getType()
        );

        System.out.println(
                "Treatment Fee  : LKR "
                + bill.getTreatmentCost()
        );

        System.out.println(
                "Consultation   : LKR "
                + bill.getConsultationFee()
        );

        System.out.println("--------------------------------");

        System.out.println(
                "TOTAL          : LKR "
                + bill.getTotalAmount()
        );

        System.out.println("================================");
        System.out.println("          THANK YOU             ");
        System.out.println("================================");
    }
}