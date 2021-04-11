package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Medicines;
import com.example.restservice.repository.MedicineRepository;
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
public class MedicineController {
    @Autowired
    MedicineRepository medicineRepository;

    @PostMapping("/medicines")
    public ResponseEntity<Medicines> createMedicine(@RequestBody Medicines medicine) {
        try {
            Medicines _medicine = medicineRepository.save(new Medicines(
                    medicine.getDrugName(),
                    medicine.getUnit(),
                    medicine.getDosage()
            ));
            return new ResponseEntity<>(_medicine, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/medicines")
    public ResponseEntity<List<Medicines>> getAllMedicines() {
        try {
            List<Medicines> medicines = new ArrayList<Medicines>();
            medicineRepository.findAll().forEach(medicines::add);

            if (medicines.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(medicines, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/medicines/{id}")
    public ResponseEntity<Medicines> getMedicine(@PathVariable("id") String id) {
        Optional<Medicines> medicineData = medicineRepository.findById(id);
        if (medicineData.isPresent()) {
            return new ResponseEntity<>(medicineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/medicines/{id}")
    public ResponseEntity<Medicines> updateMedicine(@PathVariable("id") String id, @RequestBody Medicines medicine) {
        Optional<Medicines> medicineData = medicineRepository.findById(id);
        if (medicineData.isPresent()) {
            Medicines _medicine = medicineData.get();
            _medicine.setDrugName(medicine.getDrugName());
            _medicine.setDosage(medicine.getDosage());
            _medicine.setUnit(medicine.getUnit());
            return new ResponseEntity<>(medicineRepository.save(_medicine), HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/medicines/{id}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable("id") String id) {
        try {
            medicineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
