import { Component, OnInit,OnDestroy } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { Observable,Subscription } from 'rxjs';
import {  FormBuilder } from '@angular/forms';
import { Prescription } from 'src/app/classes/Prescription';
import { PrescriptionService } from 'src/app/services/prescription.service';
import { Patient } from 'src/app/classes/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-prescription-list',
  templateUrl: './prescription-list.component.html',
  styleUrls: ['./prescription-list.component.css']
})
export class PrescriptionListComponent implements OnInit {
  patients: Observable<Patient[]>;
  patientList: Patient[];
  prescriptions: Observable<Prescription[]>;
  prescriptionByNameDob: Prescription = new Prescription();
  subscription: Subscription;
  searchForm;

  constructor(private prescriptionService: PrescriptionService,
     private router: Router, private formBuilder: FormBuilder,
     private patientService: PatientService,
     private route: ActivatedRoute) { 

    this.searchForm = this.formBuilder.group({
      patientName: '',
      dateBirth:'',          //dob added
    });
  }

  ngOnInit(): void {
    console.log('prescription list');
    this.reloadData();
    this.subscription = this.prescriptionService.getListUpdateAlert().subscribe(
      (reportMessage) => {
        if (reportMessage) {
          this.reloadData();
        }
      }
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
  

  reloadData(){
     // @ts-ignore
     this.patients = this.patientService.getAll().subscribe(
      (data: Patient[]) => {
        this.patientList = data;
        console.log(this.patientList[1]);
      }
    );

    
    this.prescriptions = this.prescriptionService.getAll();
    
  }
 

  prescripDetails(id: string) {
    this.router.navigate(['prescrip/details', id]);
  }

  addprescription() {
    this.router.navigate(['prescrip/add']);
  }
  getPatientName(patientID: bigint) {
    try {
      return this.patientList.find(p => p. regid === patientID).patientName;
    } catch ( error ) {
      return null;
    }
  }

  getPatientDob(patientID: bigint) {
    try {
      return this.patientList.find(p => p.regid === patientID).dateBirth;
    } catch ( error ) {
      return null;
    }
  }

  
  getPatientContact(patientID: bigint) {
    try {
      return this.patientList.find(p => p.regid === patientID).contactno;
    } catch ( error ) {
      return null;
    }
  }


  OnSubmitNamedob(searchNamedob){
    console.log('Search name:');
    console.log(searchNamedob.patientName);
    console.log('search dob:');
    console.log(searchNamedob.dateBirth);
    this.prescriptions = this.prescriptionService.getprescripbynameanddob(searchNamedob);
  }

 


}
