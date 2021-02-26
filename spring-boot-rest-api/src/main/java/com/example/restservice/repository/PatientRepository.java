package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends MongoRepository<Patient,String> {
    @Query("{ 'patient_name' : ?0 }")
    List<Patient> findByPatient_name(String patient_name);

    @Query("{patient_name : ?0, date_of_birth : ?1}")
    List<Patient> findByPatient_nameAndDate_of_birth(String patient_name, Date date_of_birth);
}
