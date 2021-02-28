package com.example.restservice.models.tutorial;

public class Medicines {
    private String drugName;
    private double unit;
    private double dosage;

    public Medicines(String drugName,double unit,double dosage)
    {
        this.drugName=drugName;
        this.unit=unit;
        this.dosage=dosage;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
