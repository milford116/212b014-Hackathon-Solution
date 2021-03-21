import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {  FormBuilder } from '@angular/forms';
import { Doctor } from 'src/app/classes/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';


@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  doctors: Observable<Doctor[]>;
  searchForm;

  constructor(private doctorService: DoctorService, private router: Router, private formBuilder: FormBuilder) { 
    this.searchForm = this.formBuilder.group({
      name: '',
    });
  }

  ngOnInit(): void {
    console.log('doctor list');
    this.reloadData();
  }
  reloadData(){
    this.doctors = this.doctorService.getAll();
    console.log(this.doctors);
  }

  deleteDoctor(id: string) {
    this.doctorService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }
  doctorDetails(id: string){
    this.router.navigate(['doctor/getdetails', id]);
  }
  updateDoctor(id: string){
    this.router.navigate(['doctor/getupdate', id]);
  }

  OnSubmit(searchName){
    console.log('Search name:');
    console.log(searchName.name);
    this.doctors = this.doctorService.findByDoctor_name(searchName.name);
  }




}
