package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PatientRepository extends MongoRepository<Patient,String> {
    @Query("{ 'patientName' : ?0 }")
    List<Patient> findByPatient_name(String patient_name);

    @Query("{ 'regid' : ?0 }")
    List<Patient> findByPatient_regid(int regid);

 //   @Query("{patient_name : ?0, date_of_birth : ?1}")
   List<Patient>  findByPatientNameAndDateBirth(String patientName, LocalDate dateBirth);

}
