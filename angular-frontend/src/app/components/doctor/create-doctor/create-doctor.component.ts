import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Doctor } from 'src/app/classes/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-create-doctor',
  templateUrl: './create-doctor.component.html',
  styleUrls: ['./create-doctor.component.css']
})
export class CreateDoctorComponent implements OnInit {
  doctor: Doctor = new Doctor();
  submitted = false;

  constructor(private doctorService: DoctorService,
    private router: Router) { }

  ngOnInit(): void {
  }
  newDoctor(): void {
    this.submitted = false;
    this.doctor = new Doctor();
  }

  save() {
    this.doctorService
      .create(this.doctor).subscribe(data => {
        console.log(data);
        this.doctor = new Doctor();
        console.log(this.doctor);
        this.gotoList();
      },
      error => console.log(error));
  }
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/doctors']);
  }


}
