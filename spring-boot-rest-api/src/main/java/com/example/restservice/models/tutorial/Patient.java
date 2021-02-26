package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "patients")
public class Patient {
    @Id
private String id;

    private int regid;
    private int doctorid;
    private int hospitalid;
    private int upi;
    private String patientName;
 //   @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate regdatetime;
    private LocalDate dateBirth;
    private int age;
    private String gender;
    private String occupation;
    private String healthinsuranceno;
    private String healthcareprovider;
    private String address;
    private  String contactno;
    private Date createdon;

    public Patient(int regid,int doctorid,int hospitalid,int upi,String patientName,LocalDate regdatetime,LocalDate dateBirth,
                   int age,String gender,String occupation,String healthinsuranceno,String healthcareprovider,
                   String address,String contactno,Date createdon)
    {
        this.regid=regid;
        this.doctorid=doctorid;
        this.hospitalid=hospitalid;
        this.upi=upi;
        this.patientName=patientName;
        this.regdatetime=regdatetime;
        this.dateBirth=dateBirth;
        this.age=age;
        this.gender=gender;
        this.occupation=occupation;
        this.healthinsuranceno=healthinsuranceno;
        this.healthcareprovider=healthcareprovider;
        this.address=address;
        this.contactno=contactno;
        this.createdon=createdon;
    }

    public int getregid() {
        return regid;
    }

    public void setregid(int regid) {
        this.regid = regid;
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
                ", regid=" + regid +
                ", doctorid=" + doctorid +
                ", hospitalid=" + hospitalid +
                ", upi=" + upi +
                ", patientName='" + patientName + '\'' +
                ", regdatetime=" + regdatetime +
                ", of_birth=" + dateBirth +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", healthinsuranceno='" + healthinsuranceno + '\'' +
                ", healthcareprovider='" + healthcareprovider + '\'' +
                ", address='" + address + '\'' +
                ", contactno='" + contactno + '\'' +
                ", createdon=" + createdon +
                '}';
    }

    public Date getcreatedon() {
        return createdon;
    }

    public void setcreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getcontactno() {
        return contactno;
    }

    public void setcontactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String gethealthcareprovider() {
        return healthcareprovider;
    }

    public void sethealthcareprovider(String healthcareprovider) {
        this.healthcareprovider = healthcareprovider;
    }

    public String gethealthinsuranceno() {
        return healthinsuranceno;
    }

    public void sethealthinsuranceno(String healthinsuranceno) {
        this.healthinsuranceno = healthinsuranceno;
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

    public LocalDate getdateBirth() {
        return dateBirth;
    }

    public void setof_birth(LocalDate dateBirth) {
        this.dateBirth= dateBirth;
    }

    public LocalDate getregdatetime() {
        return regdatetime;
    }

    public void setregdatetime(LocalDate regdatetime) {
        this.regdatetime = regdatetime;
    }

    public String getpatientName() {
        return patientName;
    }

    public void setpatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getUpi() {
        return upi;
    }

    public void setUpi(int upi) {
        this.upi = upi;
    }

    public int gethospitalid() {
        return hospitalid;
    }

    public void sethospitalid(int hospitalid) {
        this.hospitalid = hospitalid;
    }

    public int getdoctorid() {
        return doctorid;
    }

    public void setdoctorid(int doctorid) {
        this.doctorid = doctorid;
    }


}
