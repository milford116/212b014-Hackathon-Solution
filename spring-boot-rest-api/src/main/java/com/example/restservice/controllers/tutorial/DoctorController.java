package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Doctor;
import com.example.restservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public List<Doctor> getdoctor()
    {

        return doctorRepository.findAll();


    }

    @GetMapping("/doctors/regid/{id}")
    public ResponseEntity<List<Doctor>> getdoctorByregId(@PathVariable int id) {
        List<Doctor> doctorList= new ArrayList<Doctor>();

        try {
            doctorRepository.findByDoctor_id(id).forEach(doctorList::add);
            if (doctorList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctors/{id}")
    public Optional<Doctor> getdoctor_id(@PathVariable ("id")String id)
    {
        return doctorRepository.findById(id);
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<HttpStatus> deletedoctorbyid(@PathVariable ("id")String id) {
        try {
           doctorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/doctors")
    public ResponseEntity<HttpStatus> deleteAlldoctors() {
        try {
           doctorRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




    @PostMapping ("/doctors/{name}")

    public ResponseEntity<List<Doctor>> getdoctorname(@PathVariable ("name") String name)
    {
        try {
            List<Doctor> doctorList= new ArrayList<Doctor>();
            doctorRepository.findByDoctor_name(name).forEach(doctorList::add);

            if (doctorList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/doctors")
    public ResponseEntity<Doctor> createdoctor(@RequestBody Doctor doctor) {
        try {
           Doctor doctor1 = doctorRepository.save(new Doctor(doctor.getDoctor_id(), doctor.getHospital_id(),
                   doctor.getDoctor_name(), doctor.getSpeciality(),
                   doctor.getAddress(),
                   doctor.getAbout(),
                   doctor.getProfile_pic(),
                   doctor.getCreated_on()));
            return new ResponseEntity<>(doctor1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updatedoctor(@PathVariable ("id")String id, @RequestBody Doctor doctor) {
        Optional<Doctor> doctorData = doctorRepository.findById(id);

        if (doctorData.isPresent()) {
            Doctor doctor1 = doctorData.get();
            doctor1.setDoctor_id(doctor.getDoctor_id());
            doctor1.setHospital_id(doctor.getHospital_id());
            doctor1.setAddress(doctor.getAddress());
            doctor1.setDoctor_name(doctor.getDoctor_name());
            doctor1.setAbout(doctor.getAbout());
            doctor1.setSpeciality(doctor.getSpeciality());
            doctor1.setProfile_pic(doctor.getProfile_pic());
            return new ResponseEntity<>(doctorRepository.save(doctor1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
