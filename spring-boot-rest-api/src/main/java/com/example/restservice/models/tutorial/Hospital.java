package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hospitals")
public class Hospital {
    @Id
    private int id;

    private String hospital_name;
    private String branch_name;
    private String address;
    private String email;
    private String contact;

    public String getBranch_name() {
        return branch_name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", hospital_name='" + hospital_name + '\'' +
                ", branch_name='" + branch_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", created_on='" + created_on + '\'' +
                '}';
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCreated_on() {
        return created_on;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public void setCreated_on(String created_on) {
        this.created_on = created_on;
    }

    private String created_on;

    public Hospital()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hospital(int id, String hospital_name, String branch_name, String address, String email, String contact, String created_on)
    {
        this.address=address;
        this.branch_name=branch_name;
        this.contact=contact;
        this.id=id;
        this.hospital_name=hospital_name;
        this.email=email;
        this.created_on=created_on;
    }

}

