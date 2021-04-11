package com.example.restservice.controllers.tutorial;

import com.example.restservice.models.tutorial.Test;
import com.example.restservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TestController {
    @Autowired
    TestRepository testRepository;

    @PostMapping("/tests")
    public ResponseEntity<Test> createtest(@RequestBody Test test) {
        try {
            Test test1 = testRepository.save(new Test(test.getItemNo(),test.getItemId(),
                    test.getItemName(),test.getSampleName(),
                    test.getPrice(),test.getTestName()));
            return new ResponseEntity<>(test1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tests")
    public List<Test> gettest()
    {

        return testRepository.findAll();
    }

    @GetMapping("/tests/{id}")
    public ResponseEntity<Test> gettestById(@PathVariable("id") String id) {
        Optional<Test> testData = testRepository.findById(id);

        if (testData.isPresent()) {
            return new ResponseEntity<>(testData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tests/{id}")
    public ResponseEntity<Test> updatetest(@PathVariable("id") String id, @RequestBody Test test) {
        Optional<Test> testData = testRepository.findById(id);

        if (testData.isPresent()) {
            Test test1=testData.get();
            test1.setTestName(test.getTestName());
            test1.setSampleName(test.getSampleName());
            test1.setItemId(test.getItemId());
            test1.setItemName(test.getItemName());
            test1.setItemNo(test.getItemNo());
            test1.setPrice(test.getPrice());

            return new ResponseEntity<>(testRepository.save(test1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        }

    @DeleteMapping("/tests/{id}")
    public ResponseEntity<HttpStatus> deletetestbyid(@PathVariable("id")String id) {
        try {
            testRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tests")
    public ResponseEntity<HttpStatus> deleteAlltests() {
        try {
            testRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }




}
