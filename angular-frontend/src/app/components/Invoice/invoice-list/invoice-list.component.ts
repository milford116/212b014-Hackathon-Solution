import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable ,Subscription} from 'rxjs';
import {  FormBuilder } from '@angular/forms'
import { Invoice } from 'src/app/classes/Invoice';
import { InvoiceService } from 'src/app/services/invoice.service';
import { Patient } from 'src/app/classes/Patient';
import { PatientService } from 'src/app/services/patient.service';
@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {
  patients: Observable<Patient[]>;
  patientList: Patient[];
  invoices: Observable<Invoice[]>;
  searchForm;
  subscription: Subscription;

  constructor(private invoiceService: InvoiceService, private router: Router, private formBuilder: FormBuilder,
    private patientService: PatientService) { 
    this.searchForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit(): void {
    console.log('invoice list');
    this.reloadData();
  }

  reloadData(){

    // @ts-ignore
    this.patients = this.patientService.getAll().subscribe(
      (data: Patient[]) => {
        this.patientList = data;
        console.log(this.patientList[1]);
      }
    );
    this.invoices = this.invoiceService.getAll();
    console.log(this.invoices);
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




  

}
