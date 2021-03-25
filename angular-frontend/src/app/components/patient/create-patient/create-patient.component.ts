import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/classes/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.css']
})
export class CreatePatientComponent implements OnInit {
  patient: Patient = new Patient();
  submitted = false;

  constructor(private patientService: PatientService,
              private router: Router) { }

  

  ngOnInit() {
  }
  newPatient(): void {
    this.submitted = false;
    this.patient = new Patient();
  }
  save() {
    this.patientService
      .create(this.patient).subscribe(data => {
        console.log(data);
        this.patient = new Patient();
        console.log(this.patient);
        this.patientService.sendListUpdateAlert('Added');
        this.gotoList();
      },
      error => console.log(error));
  }
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/patients']);
  }

}
