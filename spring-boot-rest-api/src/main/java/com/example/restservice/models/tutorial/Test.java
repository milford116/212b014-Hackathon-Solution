package com.example.restservice.models.tutorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "testitem")
public class Test {
    @Id
    private String id;

    private int itemNo;
    private String itemId;
    private String itemName;
    private String testName;
    private String sampleName;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSampleName() {
        return sampleName;
    }

    public void setSampleName(String sampleName) {
        this.sampleName = sampleName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Test(int itemNo, String itemId, String itemName, String sampleName, int price, String testName)
    {
        this.itemName=itemName;
        this.testName=testName;
        this.sampleName=sampleName;
        this.itemNo=itemNo;
        this.itemId=itemId;
        this.price=price;
    }


}
