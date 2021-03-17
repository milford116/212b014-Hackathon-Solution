import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Hospital } from 'src/app/classes/Hospital';
import { HospitalService } from 'src/app/services/hospital.service';

@Component({
  selector: 'app-create-hospital',
  templateUrl: './create-hospital.component.html',
  styleUrls: ['./create-hospital.component.css']
})
export class CreateHospitalComponent implements OnInit {
  hospital: Hospital = new Hospital();
  submitted = false;

  constructor(private hospitalService: HospitalService,
              private router: Router) { }

  

  ngOnInit(): void {
  }
  newHospital(): void {
    this.submitted = false;
    this.hospital= new Hospital();
  }
  save() {
    this.hospitalService
      .create(this.hospital).subscribe(data => {
        console.log(data);
        this.hospital = new Hospital();
        console.log(this.hospital);
        this.gotoList();
      },
      error => console.log(error));
  }
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/hospitals']);
  }

}
