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
  {path:'prescriptions',component:PrescriptionListComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
