package com.example.restservice.models.tutorial;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "doctors")
public class Doctor {

    private Integer doctor_id;

    private  int hospital_id;
    private  String doctor_name;
    private String speciality;
    private  String address;
    private String about;
    private  Boolean  profile_pic;  //eta ki korbo?/
    private Date created_on;

    public int getDoctor_id() {
        return doctor_id;
    }
    public Doctor()
    {

    }
    public Doctor (int id,int hospital_id,String doctor_name,String speciality,String address,String about,Boolean profile_pic,Date created_on)
    {
        this.doctor_id=id;
        this.doctor_name=doctor_name;
        this.address=address;
        this.hospital_id=hospital_id;
        this.speciality=speciality;
        this.about=about;
        this.profile_pic=profile_pic;
        this.created_on=created_on;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctor_id=" + doctor_id +
                ", hospital_id=" + hospital_id +
                ", doctor_name='" + doctor_name + '\'' +
                ", speciality='" + speciality + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
                ", profile_pic=" + profile_pic +
                ", created_on=" + created_on +
                '}';
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public Boolean getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(Boolean profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public void setDoctor_id(int id) {
        this.doctor_id= id;
    }
}
