package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Treatment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.service.BillingService;

public class BillingServiceTest {

    @Test
    void testBillCalculation() {

        BillingService billingService = new BillingService();

        Treatment treatment = new Treatment();
        treatment.setFee(10000.00);
        treatment.setType("Tooth Extraction");

        Appointment appointment = new Appointment();
        appointment.setTreatment(treatment);

        Bill bill = billingService.calculateBill(appointment);

        assertNotNull(bill);
        assertEquals(10000.00, bill.getTreatmentCost(), 0.01);
        assertEquals(1000.00, bill.getConsultationFee(), 0.01);
        assertEquals(11000.00, bill.getTotalAmount(), 0.01);
    }
    
    @Test
    void testNullAppointmentBillRejected() {

        BillingService billingService = new BillingService();

        Bill bill = billingService.calculateBill(null);

        assertNull(bill);
    }
}