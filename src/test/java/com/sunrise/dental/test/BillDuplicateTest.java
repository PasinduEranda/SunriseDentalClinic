package com.sunrise.dental.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;
import com.sunrise.dental.service.BillingService;

public class BillDuplicateTest {

    @Test
    void testDuplicateBillIsRejected() {

        BillingService billingService = new BillingService();

        Appointment appointment = new Appointment();
        appointment.setAppointmentNo(3);

        Bill bill = new Bill();
        bill.setAppointment(appointment);
        bill.setTreatmentCost(10000.00);
        bill.setConsultationFee(1000.00);

        boolean result = billingService.saveBill(bill);

        assertFalse(result);
    }
}