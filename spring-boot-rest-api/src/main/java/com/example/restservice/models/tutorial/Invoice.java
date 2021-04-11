package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "invoice")
public class Invoice {
    @Id
    private String id;

    private int regId;
    @CreatedDate
    private Date createdOn;
    List<Test> testList;
    private int invoiceId;
    public Invoice(int regId,List<Test> testList,int invoiceId)
    {

        this.regId=regId;
        this.createdOn=new Date();
        this.testList=testList;
        this.invoiceId=invoiceId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

//    public LocalDate getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(LocalDate createdOn) {
//        this.createdOn = createdOn;
//    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
