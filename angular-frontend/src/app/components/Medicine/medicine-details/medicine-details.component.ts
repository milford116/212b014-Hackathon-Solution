import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Medicines } from 'src/app/classes/Medicine';
import { MedicinesService } from 'src/app/services/medicines.service';

@Component({
  selector: 'app-medicine-details',
  templateUrl: './medicine-details.component.html',
  styleUrls: ['./medicine-details.component.css']
})
export class MedicineDetailsComponent implements OnInit {
  medicineId: string;
  medicine: Medicines;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private medicineService: MedicinesService) { }

  ngOnInit(): void {
    this.medicine = new Medicines();
    this.route.params.subscribe(
      (params: Params) => {
        this.medicineId = params['id'.toString()];
        this.medicineService.getMedicine(this.medicineId).subscribe(
          medicineData => {
            this.medicine = medicineData;
          }
        );
      }
    );
  }

  deleteMedicine(id: string) {
    this.medicineService.deleteMedicine(id).subscribe(
      data => {
        this.medicineService.sendListUpdateAlert('Deleted');
      },
      error => console.log(error)
    );
    this.router.navigate([ 'medicines' ]);
  }

  updateMedicine(id: string) {
    this.router.navigate([ 'medicines/update', id ]);
  }

}
