import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Patient } from 'src/app/classes/Patient';
import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {
  id: string;
  patient: Patient;

  constructor(private route: ActivatedRoute, private router: Router,
    private patientService: PatientService) { }

  ngOnInit() {
    this.patient = new Patient();

    this.id = this.route.snapshot.params['id'];

    this.patientService.get(this.id)
      .subscribe(data => {
        console.log(data);
        this.patient = data;
      }, error => console.log(error));
  }
  list(){
    this.router.navigate(['patients']);
  }
  updateLink(){
    this.router.navigate(['update/' + this.patient.id]);  //link thik kora lagbe
  }

}
