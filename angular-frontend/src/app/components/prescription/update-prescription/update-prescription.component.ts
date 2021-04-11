import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/classes/Patient';
import { Observable } from 'rxjs';
import { Prescription } from 'src/app/classes/Prescription';
import { Doctor } from 'src/app/classes/Doctor';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Medicines } from 'src/app/classes/Medicine';
import { PatientService } from 'src/app/services/patient.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PrescriptionService } from 'src/app/services/prescription.service';
import { MedicinesService } from 'src/app/services/medicines.service';

@Component({
  selector: 'app-update-prescription',
  templateUrl: './update-prescription.component.html',
  styleUrls: ['./update-prescription.component.css']
})
export class UpdatePrescriptionComponent implements OnInit {
  prescription: Prescription = new Prescription();
  reportID: string;
  patientID: string;
  patients: Observable<Patient[]>;
  doctors: Observable<Doctor[]>;
  allergyList: string[];
  disabilityList: string[];
  medicineList: Observable<Medicines[]>;
  dietList: string[];

  // Build Report Form
  reportForm = this.fb.group({
    regId: [ '', Validators.required ],
    doctorId: [ '', Validators.required ],
    bloodPressure: [ '', Validators.required ],
    pulseRate: [ '', Validators.required ],
    weight: [ '', Validators.required ],
    allergies: this.fb.array([
      this.fb.control('')
    ]),
    disabilities: this.fb.array([
      this.fb.control('')
    ]),
    medicines: this.fb.array([]),
    diets: this.fb.array([]),
    history: [ '', Validators.required ],
    followDoctorId: [ '', Validators.required ]
  });


  constructor(private patientService: PatientService,
    private doctorService: DoctorService,
    private prescripService: PrescriptionService,
    private medicineService:MedicinesService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) { }

     // Form array functions

  get allergies() {
    return this.reportForm.get('allergies') as FormArray;
  }

  get disabilities() {
    return this.reportForm.get('disabilities') as FormArray;
  }

  get medicines() {
    return this.reportForm.get('medicines') as FormArray;
  }

  get diets() {
    return this.reportForm.get('diets') as FormArray;
  }

  addAllergies() {
    this.allergies.push(this.fb.control(''));
  }

  removeAllergies(i: number) {
    this.allergies.removeAt(i);
  }

  addDisabilities() {
    this.disabilities.push(this.fb.control(''));
  }

  removeDisabilities(i: number) {
    this.disabilities.removeAt(i);
  }
  addMedicines() {
    this.medicines.push(this.buildMedicine());
  }

  removeMedicines(i: number) {
    this.medicines.removeAt(i);
  }


  

  addDiets() {
    this.diets.push(this.buildDiet());
  }

  removeDiets(i: number) {
    this.diets.removeAt(i);
  }

  // Form array functions

  ngOnInit() {
    this.doctors = this.doctorService.getAll();
    this.patients = this.patientService.getAll();
    this.allergyList = this.prescripService.getAllergies();
    this.disabilityList = this.prescripService.getDisabilities();
    this.medicineList = this.medicineService.getAllMedicine();
    this.dietList = this.prescripService.getDiets();
    this.reportID = this.route.snapshot.params['id'.toString()];
    this.prescripService.get(this.reportID).subscribe(
      reportData => {
        this.prescription = reportData;
        this.reportForm.patchValue({
          regId: this.prescription.regId,
          doctorId: this.prescription.doctorId,
          bloodPressure: this.prescription.bloodPressure,
          pulseRate: this.prescription.pulseRate,
          weight: this.prescription.weight,
          history: this.prescription.history,
          followDoctorId: this.prescription.followDoctorId
        });
        this.reportForm.setControl('allergies', this.fb.array(this.prescription.allergies || []));
        this.reportForm.setControl('disabilities', this.fb.array(this.prescription.disabilities || []));
        if (this.prescription.medicines) {
          for (const medicine of this.prescription.medicines) {
            this.medicines.push(
              new FormGroup({
                drugName: new FormControl(medicine.drugName, Validators.required),
                unit: new FormControl(medicine.unit, Validators.required),
                dosage: new FormControl(medicine.dosage, Validators.required),
              })
            );
          }
        }

        if (this.prescription.diets) {
          for (const diet of this.prescription.diets) {
            this.diets.push(
              new FormGroup({
                name: new FormControl(diet.name, Validators.required),
                description: new FormControl(diet.description, Validators.required)
              })
            );
          }
        }
      }
    );
  }

  update() {
    this.prescription = this.reportForm.value;
    this.prescripService
      .update(this.reportID, this.prescription).subscribe(reportData => {
        this.prescription = reportData;
        this.prescripService.sendListUpdateAlert('Updated');
        this.gotoList();
      },
      error => console.log(error));
  }

  onSubmit() {
    this.update();
  }
  gotoList() {
    this.router.navigate([ 'prescrip/details', this.reportID ]);
  }

  cancelAdd() {
    this.router.navigate([ 'prescriptions' ]);
  }
  // Build Medicine form
  private buildMedicine(): FormGroup {
    return this.fb.group({
      drugName: [ '', Validators.required ],
      unit: [ '', Validators.required ],
      dosage: [ '', Validators.required ],
    });
  }

  // Build Diet form
  private buildDiet(): FormGroup {
    return this.fb.group({
      name: [ '', Validators.required ],
      description: [ '', Validators.required ],
    });
  }

}
