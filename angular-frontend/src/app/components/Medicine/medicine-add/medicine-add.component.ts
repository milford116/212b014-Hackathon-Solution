import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { Medicines } from 'src/app/classes/Medicine';
import { MedicinesService } from 'src/app/services/medicines.service';

@Component({
  selector: 'app-medicine-add',
  templateUrl: './medicine-add.component.html',
  styleUrls: ['./medicine-add.component.css']
})
export class MedicineAddComponent implements OnInit {
  medicine = new Medicines();
  medicineId: string;
  submitted = false;

  // Build Report Form
  medicineForm = this.fb.group({
    drugName: [ '', Validators.required ],
    unit: [ '', Validators.required ],
    dosage: [ '', Validators.required ],
  });

  constructor(private medicineService: MedicinesService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  save() {
    this.medicine = this.medicineForm.value;
    this.medicineService.createMedicine(this.medicine).subscribe(
      medicineData => {
        this.medicine = medicineData;
        console.log(this.medicine);
        this.medicineId = this.medicine.id;
        this.medicine = new Medicines();
        this.medicineService.sendListUpdateAlert('Added');
        this.goToList();
      }, error => console.log(error)
    );
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goToList() {
    this.router.navigate([ 'medicines/details', this.medicineId ]);
  }

  cancelAdd() {
    this.router.navigate([ 'medicines' ]);
  }

}
