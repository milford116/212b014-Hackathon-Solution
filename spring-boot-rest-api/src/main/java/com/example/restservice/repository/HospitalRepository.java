package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface HospitalRepository extends MongoRepository<Hospital,Integer> {
    @Query("{ 'hospital_name' : ?0 }")
List<Hospital> findByHospital_nameContaining
            (String hospital_name);

}


