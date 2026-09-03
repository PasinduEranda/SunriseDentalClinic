package com.sunrise.dental.util;

public class ValidationUtil {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidName(String name) {
        if (isEmpty(name)) {
            return false;
        }

        return name.matches("[a-zA-Z .]+");
    }

    public static boolean isValidContactNumber(String contactNumber) {
        if (isEmpty(contactNumber)) {
            return false;
        }

        return contactNumber.matches("\\d{10}");
    }

    public static boolean isValidAppointmentNumber(int appointmentNo) {
        return appointmentNo > 0;
    }
}