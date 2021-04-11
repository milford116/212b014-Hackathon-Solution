package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice,String> {
    List<Invoice> findByRegId(int regId);
}
