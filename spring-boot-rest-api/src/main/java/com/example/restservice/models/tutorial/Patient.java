package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "patients")
public class Patient {
    @Id
private String id;

    private int reg_id;
    private int doctor_id;
    private int hospital_id;
    private int upi;
    private String patient_name;
    private Date reg_datetime;
    private Date date_of_birth;
    private int age;
    private String gender;
    private String occupation;
    private String health_insurance_no;
    private String health_care_provider;
    private String address;
    private  String contact_no;
    private Date created_on;

    public Patient(int reg_id,int doctor_id,int hospital_id,int upi,String patient_name,Date reg_datetime,Date date_of_birth,
                   int age,String gender,String occupation,String health_insurance_no,String health_care_provider,
                   String address,String contact_no,Date created_on)
    {
        this.reg_id=reg_id;
        this.doctor_id=doctor_id;
        this.hospital_id=hospital_id;
        this.upi=upi;
        this.patient_name=patient_name;
        this.reg_datetime=reg_datetime;
        this.date_of_birth=date_of_birth;
        this.age=age;
        this.gender=gender;
        this.occupation=occupation;
        this.health_insurance_no=health_insurance_no;
        this.health_care_provider=health_care_provider;
        this.address=address;
        this.contact_no=contact_no;
        this.created_on=created_on;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", reg_id=" + reg_id +
                ", doctor_id=" + doctor_id +
                ", hospital_id=" + hospital_id +
                ", upi=" + upi +
                ", patient_name='" + patient_name + '\'' +
                ", reg_datetime=" + reg_datetime +
                ", date_of_birth=" + date_of_birth +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", health_insurance_no='" + health_insurance_no + '\'' +
                ", health_care_provider='" + health_care_provider + '\'' +
                ", address='" + address + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", created_on=" + created_on +
                '}';
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHealth_care_provider() {
        return health_care_provider;
    }

    public void setHealth_care_provider(String health_care_provider) {
        this.health_care_provider = health_care_provider;
    }

    public String getHealth_insurance_no() {
        return health_insurance_no;
    }

    public void setHealth_insurance_no(String health_insurance_no) {
        this.health_insurance_no = health_insurance_no;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Date getReg_datetime() {
        return reg_datetime;
    }

    public void setReg_datetime(Date reg_datetime) {
        this.reg_datetime = reg_datetime;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public int getUpi() {
        return upi;
    }

    public void setUpi(int upi) {
        this.upi = upi;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }


}
