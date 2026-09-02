package com.sunrise.dental.model;

public class Dentist {

    private int dentistId;
    private String name;

    public Dentist() {
    }

    public Dentist(int dentistId, String name) {
        this.dentistId = dentistId;
        this.name = name;
    }

    public int getDentistId() {
        return dentistId;
    }

    public void setDentistId(int dentistId) {
        this.dentistId = dentistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}