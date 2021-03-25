package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {
    @Id
private String id;

    private long  prescriptionId;
    private int regId;
    private int doctorId;
    private LocalDate createdOn;
    private LocalDate modifiedOn;
    private  double bloodPressure;
    private double pulseRate;
    private double weight;
    List<String>allergies;
    List<String> disabilities;
    List<Medicines>medicines;
    List<Diets> diets;
    private String history;
    private int followDoctorId;



    public Prescription(long prescriptionId,int regId,int doctorId,LocalDate createdOn,LocalDate modifiedOn,double bloodPressure,
                        double pulseRate,double weight,List<String>allergies,List<String>disabilities,List<Medicines>medicines,
                        List<Diets>diets,String history,int followDoctorId)
    {

        this.prescriptionId=prescriptionId;this.regId=regId;
        this.doctorId=doctorId;this.createdOn=createdOn;
        this.modifiedOn=modifiedOn;this.bloodPressure=bloodPressure;
        this.pulseRate=pulseRate;this.weight=weight;
        this.allergies=allergies;this.disabilities=disabilities;
        this.medicines=medicines;this.diets=diets;
        this.history=history;this.followDoctorId=followDoctorId;
    }
    public Prescription()
    {

    }

    public int getFollowDoctorId() {
        return followDoctorId;
    }

    public void setFollowDoctorId(int followDoctorId) {
        this.followDoctorId = followDoctorId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public List<String> getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(List<String> disabilities) {
        this.disabilities = disabilities;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(double pulseRate) {
        this.pulseRate = pulseRate;
    }

    public double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public LocalDate getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDate modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Diets> getDiets() {
        return diets;
    }

    public void setDiets(List<Diets> diets) {
        this.diets = diets;
    }

    public List<Medicines> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicines> medicines) {
        this.medicines = medicines;
    }
}
