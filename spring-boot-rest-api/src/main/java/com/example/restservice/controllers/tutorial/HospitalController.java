package com.example.restservice.controllers.tutorial;


import com.example.restservice.models.tutorial.Hospital;
import com.example.restservice.repository.HospitalRepository;
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
public class HospitalController {
@Autowired
    HospitalRepository hospitalRepository;
@GetMapping("/hospitals")
public List<Hospital> gethospital()
{

        return hospitalRepository.findAll();


}
@GetMapping("/hospitals/{id}")
public Optional<Hospital> gethospital_id(@PathVariable int id)
{
   return hospitalRepository.findById(id);
}

    @PostMapping ("/hospitals/{name}")

    public ResponseEntity<List<Hospital>> gethospitalname(@PathVariable ("name") String name)
    {
        try {
            List<Hospital> hospitalList= new ArrayList<Hospital>();
               hospitalRepository.findByHospital_nameContaining(name).forEach(hospitalList::add);

            if (hospitalList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hospitalList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
@PostMapping("/hospitals")
public ResponseEntity<Hospital> createhospital(@RequestBody Hospital hospital) {
    try {
       Hospital hospital1 = hospitalRepository.save(new Hospital(hospital.getId(),hospital.getHospital_name(),hospital.getBranch_name(),hospital.getAddress(),hospital.getEmail(),hospital.getContact(),hospital.getCreated_on()));
        return new ResponseEntity<>(hospital1, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<Hospital> updatehospital(@PathVariable int id, @RequestBody Hospital hospital) {
        Optional<Hospital> hospitalData = hospitalRepository.findById(id);

        if (hospitalData.isPresent()) {
            Hospital hospital1 = hospitalData.get();
            hospital1.setHospital_name(hospital.getHospital_name());
            hospital1.setBranch_name(hospital.getBranch_name());
            hospital1.setContact(hospital.getContact());
           hospital1.setAddress(hospital.getAddress());
           hospital1.setEmail(hospital.getEmail());
           hospital1.setId(hospital.getId());
           hospital1.setCreated_on(hospital.getCreated_on());
            System.out.println("updated");
            return new ResponseEntity<>(hospitalRepository.save(hospital1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/hospitals/{id}")
    public ResponseEntity<HttpStatus> deletehospitalbyid(@PathVariable int id) {
        try {
           hospitalRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hospitals")
    public ResponseEntity<HttpStatus> deleteAllhospitals() {
        try {
            hospitalRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}


