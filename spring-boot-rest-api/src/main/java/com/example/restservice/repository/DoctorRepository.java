package com.example.restservice.repository;

import com.example.restservice.models.tutorial.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends MongoRepository<Doctor,Integer> {
    @Query("{ 'doctor_name' : ?0 }")
    List<Doctor> findByDoctor_name(String doctor_name);

    @Query("{ 'doctor_id' : ?0}")
    Optional<Doctor> findByDoctor_id(Integer doctor_id);

    @Query(value = "{ doctor_id: ?0}", delete = true)
    Long deletedoctorbyid(Integer doctor_id);

}
