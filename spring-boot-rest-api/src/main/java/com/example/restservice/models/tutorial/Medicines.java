package com.example.restservice.models.tutorial;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicines")
public class Medicines {
    @Id
    private String id;
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

    public String getId() {
        return id;
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
