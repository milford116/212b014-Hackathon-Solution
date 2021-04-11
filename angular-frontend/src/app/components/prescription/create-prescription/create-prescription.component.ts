import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Prescription } from 'src/app/classes/Prescription';
import { Patient } from 'src/app/classes/Patient';
import { Doctor } from 'src/app/classes/Doctor';
import { Medicines } from 'src/app/classes/Medicine';
import { PatientService } from 'src/app/services/patient.service';
import { DoctorService } from 'src/app/services/doctor.service';
import { PrescriptionService } from 'src/app/services/prescription.service';
import { MedicinesService } from 'src/app/services/medicines.service';

@Component({
  selector: 'app-create-prescription',
  templateUrl: './create-prescription.component.html',
  styleUrls: ['./create-prescription.component.css']
})
export class CreatePrescriptionComponent implements OnInit {
  prescription: Prescription = new Prescription();
  submitted = false;
  reportID: string;
  patientID: string;
  patients: Observable<Patient[]>;
  doctors: Observable<Doctor[]>;
  patient: Patient;
  allergyList: string[];
  disabilityList: string[];
  medicineList: Observable<Medicines[]>;
  dietList: string[];
  selectedMedicine: Medicines = new Medicines();

  //report form
  reportForm = this.fb.group({
    //patientid: [ '', Validators.required ],
    regId:[ '', Validators.required ],
    //doctorid: [ '', Validators.required ],
    doctorId:[ '', Validators.required ],
    bloodPressure: [ '', Validators.required ],
    pulseRate: [ '', Validators.required ],
    weight: [ '', Validators.required ],
    allergies: this.fb.array([
      this.fb.control('')
    ]),
    disabilities: this.fb.array([
      this.fb.control('')
    ]),
    medicines: this.fb.array([ this.buildMedicine() ]),
    diets: this.fb.array([ this.buildDiet() ]),
    history: [ '', Validators.required ],
    followDoctorId: [ '', Validators.required ]
  });

  constructor(private patientService: PatientService,
    private doctorService: DoctorService,
    private prescriptionService: PrescriptionService,  //MEDICINE SERVICE BAKI
    private medicineService:MedicinesService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) { }


    //GET METHODS
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

    //ADD/REMOVE FUNCT

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

   

  ngOnInit() {

    this.doctors = this.doctorService.getAll();
    this.patients = this.patientService.getAll();
    this.allergyList = this.prescriptionService.getAllergies();
    this.disabilityList = this.prescriptionService.getDisabilities();
    this.medicineList = this.medicineService.getAllMedicine();
    this.dietList = this.prescriptionService.getDiets();
    this.patientID = this.route.snapshot.params['id'.toString()];
    this.patient = new Patient();
    if (this.patientID) {
      try {
        this.patientService.get(this.patientID).subscribe(
          patientData => {
            this.patient = patientData;
            this.reportForm.patchValue({
              patientid: this.patient.id,
              doctorid: this.patient.doctorid
            });
            console.log("hehehe");
            console.log(this.patient);
          }
        );
      } catch ( e ) {
        console.log('Failed to load patient data');
      }
    }
  }

  save() {
    this.prescription = this.reportForm.value;
    //console.log(this.prescription[0]);
    
    this.prescriptionService
      .create(this.prescription).subscribe(prescriptionData => {
        this.prescription = prescriptionData;
       // console.log(this.prescription.id);
        this.reportID = this.prescription.id;
        this.prescription = new Prescription();
        
        this.prescriptionService.sendListUpdateAlert('Added');
        this.gotoList();
      },
      error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }
  setMedicine(index: string) {
    this.medicineService.getMedicine(index).subscribe(
      medicineData => {
        this.selectedMedicine = medicineData;
        console.log(this.selectedMedicine);
      }
    );
  }

  gotoList() {
    //console.log(this.reportID);
    this.router.navigate([ 'prescrip/details',this.reportID ]);
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
