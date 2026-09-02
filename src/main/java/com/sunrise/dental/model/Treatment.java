package com.sunrise.dental.model;

public class Treatment {

    private int treatmentId;
    private String type;
    private double fee;

    public Treatment() {
    }

    public Treatment(int treatmentId, String type, double fee) {
        this.treatmentId = treatmentId;
        this.type = type;
        this.fee = fee;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}