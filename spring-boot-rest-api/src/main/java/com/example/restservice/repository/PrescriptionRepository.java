package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PrescriptionRepository extends MongoRepository<Prescription,String> {

   List<Prescription> findByRegId(int regId);



}
