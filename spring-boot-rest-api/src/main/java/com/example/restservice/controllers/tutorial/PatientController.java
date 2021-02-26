package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Patient;
import com.example.restservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/patients")
    public ResponseEntity<Patient> createpatient(@RequestBody Patient patient) {
        try {
           Patient patient1 = patientRepository.save(new Patient(patient.getReg_id(),
                   patient.getDoctor_id(),patient.getHospital_id(),
                   patient.getUpi(),patient.getPatient_name(),
                   patient.getReg_datetime(),patient.getDate_of_birth(),
                   patient.getAge(),patient.getGender(),
                   patient.getOccupation(),patient.getHealth_insurance_no(),
                   patient.getHealth_care_provider(),patient.getAddress(),
                   patient.getContact_no(),patient.getCreated_on()));
            return new ResponseEntity<>(patient1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/patients")
    public List<Patient> getpatient()
    {

        return patientRepository.findAll();


    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patient> getpatientById(@PathVariable("id") String id) {
        Optional<Patient> patientData = patientRepository.findById(id);

        if (patientData.isPresent()) {
            return new ResponseEntity<>(patientData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping ("/patients/{name}")

    public ResponseEntity<List<Patient>> getpatientname(@PathVariable ("name") String name)
    {
        try {
            List<Patient> patientList= new ArrayList<Patient>();

patientRepository.findByPatient_name(name).forEach(patientList::add);
            if (patientList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(patientList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping ("/patients/{name}/{dob}")
    public ResponseEntity<List<Patient>> getpatientnameanddob(@PathVariable  String name,@PathVariable String dob)
    {
        try {
            Date date=new SimpleDateFormat("yyyy/MM/dd").parse(dob);
            List<Patient> patientList= new ArrayList<Patient>();

            patientRepository.findByPatient_nameAndDate_of_birth(name,date).forEach(patientList::add);
            if (patientList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(patientList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatepatient(@PathVariable("id") String id, @RequestBody Patient patient) {
        Optional<Patient> patientData = patientRepository.findById(id);

        if (patientData.isPresent()) {
            Patient patient1=patientData.get();
            patient1.setReg_id(patient.getReg_id());
            patient1.setHospital_id(patient.getHospital_id());
            patient1.setPatient_name(patient.getPatient_name());
            patient1.setAge(patient.getAge());
            patient1.setAddress(patient.getAddress());
            patient1.setDoctor_id(patient.getDoctor_id());
            patient1.setContact_no(patient.getContact_no());
            patient1.setCreated_on(patient.getCreated_on());
            patient1.setDate_of_birth(patient.getDate_of_birth());
            patient1.setGender(patient.getGender());
            patient1.setHealth_care_provider(patient.getHealth_care_provider());
            patient1.setHealth_insurance_no(patient.getHealth_insurance_no());
            patient1.setUpi(patient.getUpi());
            patient1.setOccupation(patient.getOccupation());
            patient1.setReg_datetime(patient.getReg_datetime());

            return new ResponseEntity<>(patientRepository.save(patient1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<HttpStatus> deletepatientbyid(@PathVariable("id")String id) {
        try {
           patientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/patients")
    public ResponseEntity<HttpStatus> deleteAllpatients() {
        try {
            patientRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
