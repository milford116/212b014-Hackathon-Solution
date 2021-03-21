import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Doctor } from 'src/app/classes/Doctor';
import { DoctorService } from 'src/app/services/doctor.service';
@Component({
  selector: 'app-update-doctor',
  templateUrl: './update-doctor.component.html',
  styleUrls: ['./update-doctor.component.css']
})
export class UpdateDoctorComponent implements OnInit {
  id: string;
  doctor: Doctor;

  constructor(private route: ActivatedRoute, private router: Router,
    private doctorService: DoctorService) { }

  ngOnInit(): void {
    this.doctor = new Doctor();

    this.id = this.route.snapshot.params['id'];

    this.doctorService.get(this.id)
      .subscribe(data => {
        console.log(data);
        this.doctor = data;
      }, error => console.log(error));
  }

  updateDoctor(){
    // this.tutorial = new Tutorial();
    // this.id = this.route.snapshot.params['id'];
    this.doctorService.update(this.id, this.doctor).subscribe(
      data => {
        console.log(data);
        this.doctor = new Doctor();
        this.list();
      }, error => console.log(error)
    );
  }
  onSubmit(){
    this.updateDoctor();
  }
  list(){
    this.router.navigate(['doctors']);
  }

}
