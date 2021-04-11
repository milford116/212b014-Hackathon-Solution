package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Patient;
import com.example.restservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
           Patient patient1 = patientRepository.save(new Patient(patient.getregid(),
                   patient.getdoctorid(),patient.gethospitalid(),
                   patient.getUpi(),patient.getpatientName(),
                   patient.getregdatetime(),patient.getdateBirth(),
                   patient.getAge(),patient.getGender(),
                   patient.getOccupation(),patient.gethealthinsuranceno(),
                   patient.gethealthcareprovider(),patient.getAddress(),
                   patient.getcontactno(),patient.getcreatedon()));
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

    @GetMapping("/patients/regid/{id}")
    public ResponseEntity<List<Patient>> getpatientByregId(@PathVariable int id) {
        List<Patient> patientList= new ArrayList<Patient>();

        try {
            patientRepository.findByPatient_regid(id).forEach(patientList::add);
            if (patientList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            return new ResponseEntity<>(patientList, HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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

    @PostMapping ("/patients/namedatebirth/{patientName}/{dateBirth}")
    public ResponseEntity<List<Patient>> getpatientnameanddob(@PathVariable  String patientName,@PathVariable String dateBirth)
    {
        try {
            LocalDate date=LocalDate.parse(dateBirth);
            List<Patient> patientList= new ArrayList<Patient>();

            patientRepository.findByPatientNameAndDateBirth(patientName,date).forEach(patientList::add);
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
            patient1.setregid(patient.getregid());
            patient1.sethospitalid(patient.gethospitalid());
            patient1.setpatientName(patient.getpatientName());
            patient1.setAge(patient.getAge());
            patient1.setAddress(patient.getAddress());
            patient1.setdoctorid(patient.getdoctorid());
            patient1.setcontactno(patient.getcontactno());
            patient1.setcreatedon(patient.getcreatedon());
            patient1.setof_birth(patient.getdateBirth());
            patient1.setGender(patient.getGender());
            patient1.sethealthcareprovider(patient.gethealthcareprovider());
            patient1.sethealthinsuranceno(patient.gethealthinsuranceno());
            patient1.setUpi(patient.getUpi());
            patient1.setOccupation(patient.getOccupation());
            patient1.setregdatetime(patient.getregdatetime());

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
