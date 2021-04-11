import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Doctor } from 'src/app/classes/Doctor';
import { Patient } from 'src/app/classes/Patient';
import { Prescription } from 'src/app/classes/Prescription';
import { DoctorService } from 'src/app/services/doctor.service';
import { PatientService } from 'src/app/services/patient.service';
import { PrescriptionService } from 'src/app/services/prescription.service';

@Component({
  selector: 'app-prescription-details',
  templateUrl: './prescription-details.component.html',
  styleUrls: ['./prescription-details.component.css']
})
export class PrescriptionDetailsComponent implements OnInit {
  reportId: string;
  doctor: Doctor = new Doctor();
  followUpDoctor: Doctor = new Doctor();
  patient: Patient = new Patient();
  prescription: Prescription = new Prescription();

  constructor(private route: ActivatedRoute, private router: Router,
    private doctorService: DoctorService,
    private patientService: PatientService,
    private prescripService: PrescriptionService) { }

   ngOnInit() {
    this.doctor = new Doctor();
    this.route.params.subscribe(
      (params: Params) => {
        this.reportId = params['id'.toString()];
       
        this.prescripService.get(this.reportId)
          .subscribe(data => {
            // get report data
            this.prescription = data;
            console.log("report id:");
            console.log(this.reportId);
            // get doctor data
            this.doctorService.getid(this.prescription.doctorId).subscribe(
              doctorData => {
                this.doctor = doctorData;
                console.log(this.doctor.doctor_name);
              }
            );
              // get follow up doctor data
              this.doctorService.getid(this.prescription.doctorId).subscribe(
                followUpDoctorData => {
                  this.followUpDoctor = followUpDoctorData;
                }
              );
              // get patient data
              this.patientService.getid(this.prescription.regId).subscribe(
                patientData => {
                  this.patient = patientData;
                  // console.log("hheeename");
                  // console.log(this.patient[0].patientName);
                }
              );
            }, error => console.log(error));
        }
      );
   
  }

  deleteReport(id: string) {
    this.prescripService.delete(id)
      .subscribe(
        data => {
          console.log(data);
          this.prescripService.sendListUpdateAlert('Deleted');
        },
        error => console.log(error));
    this.router.navigate([ 'prescriptions' ]);
  }

  updateReport(id: string) {
    this.router.navigate([ 'prescrip/update', id ]);
  }
  generatePrescription(id: string) {
    this.router.navigate([ 'prescriptions', id ]);
  }

 }
