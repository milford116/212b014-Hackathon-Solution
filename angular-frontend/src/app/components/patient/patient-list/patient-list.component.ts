import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {  FormBuilder } from '@angular/forms'
import { Patient } from 'src/app/classes/Patient';
import {PatientService} from '../../../services/patient.service';
@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients: Observable<Patient[]>;
  searchForm;

  constructor(private patientService: PatientService, private router: Router, private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit(): void {
    console.log('patient list');
    this.reloadData();
  }

  reloadData(){
    this.patients = this.patientService.getAll();
    console.log(this.patients);
  }

  deletePatient(id: string) {
    this.patientService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  patientDetails(id: string){
    this.router.navigate(['patients/details', id]);
  }
  updatePatient(id: string){
    this.router.navigate(['update', id]);
  }

  OnSubmit(searchName){
    console.log('Search name:');
    console.log(searchName.name);
    this.patients = this.patientService.findByPatient_name(searchName.name);
  }

}
