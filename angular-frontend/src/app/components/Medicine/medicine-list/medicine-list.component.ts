import { Component, OnInit,OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subscription } from 'rxjs';
import { Medicines } from 'src/app/classes/Medicine';
import { MedicinesService } from 'src/app/services/medicines.service';

@Component({
  selector: 'app-medicine-list',
  templateUrl: './medicine-list.component.html',
  styleUrls: ['./medicine-list.component.css']
})
export class MedicineListComponent implements OnInit,OnDestroy {
  subscription: Subscription;
  medicines: Observable<Medicines[]>;

  constructor(private medicineService: MedicinesService,
    private router: Router,
    private route: ActivatedRoute) { }


    ngOnInit(): void {
      this.reloadData();
      this.subscription = this.medicineService.getListUpdateAlert().subscribe(
        (message) => {
          if (message) {
            this.reloadData();
          }
        }
      );
    }
  
    reloadData() {
      this.medicines = this.medicineService.getAllMedicine();
    }
    medicineDetails(id: string) {
      this.router.navigate(['medicines/details', id]);
    }
  
    addMedicine() {
      this.router.navigate(['medicines/add']);
    }
  
    ngOnDestroy() {
      this.subscription.unsubscribe();
    }
  

}
