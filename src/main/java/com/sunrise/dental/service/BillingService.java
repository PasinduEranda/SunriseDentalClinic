package com.sunrise.dental.service;

import com.sunrise.dental.dao.BillingDAO;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;

public class BillingService {

    private static final double CONSULTATION_FEE = 1000.00;

    private BillingDAO billingDAO;

    public BillingService() {
        billingDAO = new BillingDAO();
    }

    public Bill calculateBill(Appointment appointment) {

        if (appointment == null) {
            return null;
        }

        if (appointment.getTreatment() == null) {
            return null;
        }

        double treatmentCost =
                appointment.getTreatment().getFee();

        Bill bill = new Bill(
                0,
                treatmentCost,
                CONSULTATION_FEE,
                appointment
        );

        bill.calculateTotal();

        return bill;
    }

    public boolean saveBill(Bill bill) {

        if (bill == null) {
            return false;
        }

        if (bill.getAppointment() == null) {
            return false;
        }

        // Prevent duplicate billing for the same appointment
        Bill existingBill =
                billingDAO.findByAppointmentNo(
                        bill.getAppointment().getAppointmentNo()
                );

        if (existingBill != null) {
            return false;
        }

        return billingDAO.save(bill);
    }
    
    public Bill getExistingBill(int appointmentNo) {
        if (appointmentNo <= 0) {
            return null;
        }

        return billingDAO.findByAppointmentNo(appointmentNo);
    }

    public void printReceipt(Bill bill) {

        if (bill == null) {
            return;
        }

        System.out.println("================================");
        System.out.println("     SUNRISE DENTAL CLINIC");
        System.out.println("        BILL / RECEIPT");
        System.out.println("================================");

        System.out.println(
                "Appointment No: "
                + bill.getAppointment().getAppointmentNo()
        );

        System.out.println(
                "Patient: "
                + bill.getAppointment()
                       .getPatient()
                       .getName()
        );

        System.out.println(
                "Treatment: "
                + bill.getAppointment()
                       .getTreatment()
                       .getType()
        );

        System.out.println(
                "Treatment Cost: Rs. "
                + bill.getTreatmentCost()
        );

        System.out.println(
                "Consultation Fee: Rs. "
                + bill.getConsultationFee()
        );

        System.out.println(
                "Total Amount: Rs. "
                + bill.getTotalAmount()
        );

        System.out.println("================================");
    }
}