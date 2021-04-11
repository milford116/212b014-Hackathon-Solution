package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Invoice;
import com.example.restservice.models.tutorial.Patient;
import com.example.restservice.repository.InvoiceRepository;
import com.example.restservice.repository.PatientRepository;
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
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PatientRepository patientRepository;

    @PostMapping("/invoices")
    public ResponseEntity<Invoice> createinvoice(@RequestBody Invoice invoice) {
        try {
            Invoice invoice1 = invoiceRepository.save(new Invoice(invoice.getRegId(),
                    invoice.getTestList(),
                    invoice.getInvoiceId()));
            return new ResponseEntity<>(invoice1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoices")
    public ResponseEntity<List<Invoice>> getinvoice()
    {
        try {
            List<Invoice> invoiceList = new ArrayList<>();

            //invoiceList=invoiceRepository.findAll();
            invoiceRepository.findAll().forEach(invoiceList::add);
            System.out.println(invoiceList);
            return new ResponseEntity<>(invoiceList,HttpStatus.OK);
        }catch (Exception e)
        {
            System.out.println(e.toString());
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/invoices/{id}")
    public ResponseEntity<Invoice> getinvoiceById(@PathVariable("id") String id) {
        Optional<Invoice> testData = invoiceRepository.findById(id);

        if (testData.isPresent()) {
            return new ResponseEntity<>(testData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/invoices/{id}")
    public ResponseEntity<Invoice> updateinvoice(@PathVariable("id") String id, @RequestBody Invoice invoice) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);

        if (invoiceData.isPresent()) {
            Invoice invoice1=invoiceData.get();
            invoice1.setInvoiceId(invoice.getInvoiceId());
            //invoice1.setCreatedOn(invoice.getCreatedOn());

            invoice1.setTestList(invoice.getTestList());
            invoice1.setRegId(invoice.getRegId());

            return new ResponseEntity<>(invoiceRepository.save(invoice1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/invoices/name/dob")
    public ResponseEntity<List<Invoice>> getinvoicebynameanddob(@RequestBody Patient patient) {
        try {
            int regid;
            List<Patient> patients= new ArrayList<Patient>();
            List<Invoice>invoiceList=new ArrayList<Invoice>();

            patientRepository.findByPatientNameAndDateBirth(patient.getpatientName(),patient.getdateBirth()).forEach(patients::add);
            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for(Patient p:patients)
            {
                regid=p.getregid();
               invoiceRepository.findByRegId(regid).forEach(invoiceList::add);
            }

            if (invoiceList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(invoiceList, HttpStatus.OK);


        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<HttpStatus> deleteinvoicebyid(@PathVariable("id")String id) {
        try {
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/invoices")
    public ResponseEntity<HttpStatus> deleteAllinvoices() {
        try {
            invoiceRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
