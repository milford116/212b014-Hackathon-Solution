package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Patient;
import com.example.restservice.models.tutorial.Prescription;
import com.example.restservice.repository.PatientRepository;
import com.example.restservice.repository.PrescriptionRepository;
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
public class PrescriptionController {
    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/prescriptions")
    public ResponseEntity<Prescription> createprescription(@RequestBody Prescription prescription) {
        try {
            Prescription prescription1 = prescriptionRepository.save(new Prescription(prescription.getPrescriptionId(),
                    prescription.getRegId(), prescription.getDoctorId(), prescription.getCreatedOn(),
                    prescription.getModifiedOn(), prescription.getBloodPressure(),
                    prescription.getPulseRate(), prescription.getWeight(),
                    prescription.getAllergies(), prescription.getDisabilities(),
                    prescription.getMedicines(), prescription.getDiets(),
                    prescription.getHistory(), prescription.getFollowDoctorId()));
            return new ResponseEntity<>(prescription1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/prescriptions")
    public List<Prescription> getprescription() {

        return prescriptionRepository.findAll();


    }

    @GetMapping("/prescriptions/{id}")
    public Optional<Prescription> getprescription_id(@PathVariable("id") String id) {
        return prescriptionRepository.findById(id);
    }

    @GetMapping("/prescriptions/patient/{id}")
    public ResponseEntity<List<Prescription>> getbypatientid(@PathVariable int id)
    {
        try{
            List<Prescription> prescriptionList= new ArrayList<Prescription>();
            prescriptionRepository.findByRegId(id).forEach(prescriptionList::add);
            if (prescriptionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(prescriptionList, HttpStatus.OK);

        }catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/prescriptions/name/dob")
    public ResponseEntity<List<Prescription>> getprescripbynameanddob(@RequestBody  Patient patient) {
        try {
            int regid;
            List<Patient> patients= new ArrayList<Patient>();
            List<Prescription>prescriptionList=new ArrayList<Prescription>();

            patientRepository.findByPatientNameAndDateBirth(patient.getpatientName(),patient.getdateBirth()).forEach(patients::add);
            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

          for(Patient p:patients)
          {
               regid=p.getregid();
              prescriptionRepository.findByRegId(regid).forEach(prescriptionList::add);
           }

            if (prescriptionList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(prescriptionList, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/prescriptions/{id}")
    public ResponseEntity<Prescription> updateprescription(@PathVariable("id") String id, @RequestBody Prescription prescription) {
        Optional<Prescription> prescriptionData = prescriptionRepository.findById(id);
        if (prescriptionData.isPresent()) {
Prescription prescription1=prescriptionData.get();
prescription1.setPrescriptionId(prescription.getPrescriptionId());
prescription1.setRegId(prescription.getRegId());
prescription1.setAllergies(prescription.getAllergies());
prescription1.setBloodPressure(prescription.getBloodPressure());
prescription1.setDiets(prescription.getDiets());
prescription1.setCreatedOn(prescription.getCreatedOn());
prescription1.setDisabilities(prescription.getDisabilities());
prescription1.setDoctorId(prescription.getDoctorId());
prescription1.setHistory(prescription.getHistory());
prescription1.setMedicines(prescription.getMedicines());
prescription1.setWeight(prescription.getWeight());
prescription1.setFollowDoctorId(prescription.getFollowDoctorId());
prescription1.setModifiedOn(prescription.getModifiedOn());
            return new ResponseEntity<>(prescriptionRepository.save(prescription1), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/prescriptions/{id}")
    public ResponseEntity<HttpStatus> deleteprescripbyid(@PathVariable("id")String id) {
        try {
            prescriptionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/prescriptions")
    public ResponseEntity<HttpStatus> deleteAllprescriptons() {
        try {
            prescriptionRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
