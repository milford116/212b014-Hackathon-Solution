import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CreateTutorialComponent } from './components/tutorial/create-tutorial/create-tutorial.component';
import { TutorialDetailsComponent } from './components/tutorial/tutorial-details/tutorial-details.component';
import { UpdateTutorialComponent } from './components/tutorial/update-tutorial/update-tutorial.component';
import { TutorialListComponent } from './components/tutorial/tutorial-list/tutorial-list.component';
import {PatientListComponent} from './components/patient/patient-list/patient-list.component';
import { CreatePatientComponent } from './components/patient/create-patient/create-patient.component';
import { PatientDetailsComponent } from './components/patient/patient-details/patient-details.component';
import { UpdatePatientComponent } from './components/patient/update-patient/update-patient.component';
import { HospitalListComponent } from './components/hospital/hospital-list/hospital-list.component';
import { CreateHospitalComponent } from './components/hospital/create-hospital/create-hospital.component';
import { HospitalDetailsComponent } from './components/hospital/hospital-details/hospital-details.component';
import { UpdateHospitalComponent } from './components/hospital/update-hospital/update-hospital.component';
import { DoctorListComponent } from './components/doctor/doctor-list/doctor-list.component';
import { CreateDoctorComponent } from './components/doctor/create-doctor/create-doctor.component';
import { DoctorDetailsComponent } from './components/doctor/doctor-details/doctor-details.component';
import { UpdateDoctorComponent } from './components/doctor/update-doctor/update-doctor.component';
import { PrescriptionListComponent } from './components/prescription/prescription-list/prescription-list.component';
import { InvoiceListComponent } from './components/Invoice/invoice-list/invoice-list.component';
import { CreatePrescriptionComponent } from './components/prescription/create-prescription/create-prescription.component';
import { PrescriptionDetailsComponent } from './components/prescription/prescription-details/prescription-details.component';
import { UpdatePrescriptionComponent } from './components/prescription/update-prescription/update-prescription.component';
import { MedicineListComponent } from './components/Medicine/medicine-list/medicine-list.component';
import { MedicineDetailsComponent } from './components/Medicine/medicine-details/medicine-details.component';
import { MedicineAddComponent } from './components/Medicine/medicine-add/medicine-add.component';
import { MedicineEditComponent } from './components/Medicine/medicine-edit/medicine-edit.component';

const routes: Routes = [
  { path: '', redirectTo: 'tutorial', pathMatch: 'full' },
  { path: 'tutorials', component: TutorialListComponent},
  { path: 'details/:id', component: TutorialDetailsComponent },
  { path: 'create', component: CreateTutorialComponent },
  { path: 'update/:id', component: UpdateTutorialComponent},
  {path:'patients',component:PatientListComponent},
  {path:'patients/add',component:CreatePatientComponent},
  {path:'patients/details/:id',component:PatientDetailsComponent},
  {path:'patients/update/:id',component:UpdatePatientComponent},
  {path:'hospitals',component:HospitalListComponent},
  {path:'hospitals/create',component:CreateHospitalComponent},
  {path:'hospitals/getdetails/:id',component:HospitalDetailsComponent},
  {path:'hospitals/getupdate/:id',component:UpdateHospitalComponent},
  {path:'doctors',component:DoctorListComponent},
  {path:'doctor/create',component:CreateDoctorComponent},
  {path:'doctor/getdetails/:id',component:DoctorDetailsComponent},
  {path:'doctor/getupdate/:id',component:UpdateDoctorComponent},
  {path:'prescriptions',component:PrescriptionListComponent},
  {path:'prescrip/add',component:CreatePrescriptionComponent},
  {path:'prescrip/details/:id',component:PrescriptionDetailsComponent},
  {path:'prescrip/update/:id',component:UpdatePrescriptionComponent},
  {path:'medicines',component:MedicineListComponent},
  {path:'medicines/details/:id',component:MedicineDetailsComponent},
  {path:'medicines/add',component:MedicineAddComponent},
  {path:'medicines/update/:id',component:MedicineEditComponent},
  {path:'invoices',component:InvoiceListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
