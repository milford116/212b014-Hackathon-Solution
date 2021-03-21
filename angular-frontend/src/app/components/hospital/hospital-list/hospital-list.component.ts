import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import {  FormBuilder } from '@angular/forms';
import {Hospital} from 'src/app/classes/Hospital';
import { HospitalService } from 'src/app/services/hospital.service';

@Component({
  selector: 'app-hospital-list',
  templateUrl: './hospital-list.component.html',
  styleUrls: ['./hospital-list.component.css']
})
export class HospitalListComponent implements OnInit {
 hospitals: Observable<Hospital[]>;
  searchForm;

  constructor(private hospitalService: HospitalService, private router: Router, private formBuilder: FormBuilder)
   { 
    this.searchForm = this.formBuilder.group({
      name: '',
    });
   }

  ngOnInit(): void {
    console.log('Hospital list');
    this.reloadData();
  }
  reloadData(){
    this.hospitals= this.hospitalService.getAll();
    console.log(this.hospitals);
  }

  deleteHospital(id: bigint) {
    this.hospitalService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  hospitalDetails(id: bigint){
    this.router.navigate(['hospitals/getdetails', id]);
  }
  updateHospital(id: bigint){
    this.router.navigate(['hospitals/getupdate', id]);
  }

  OnSubmit(searchName){
    console.log('Search name:');
    console.log(searchName.name);
    this.hospitals = this.hospitalService.findByHospital_name(searchName.name);
  }

}
