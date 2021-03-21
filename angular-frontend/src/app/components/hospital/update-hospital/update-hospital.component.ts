import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Hospital } from 'src/app/classes/Hospital';
import {HospitalService} from 'src/app/services/hospital.service';
@Component({
  selector: 'app-update-hospital',
  templateUrl: './update-hospital.component.html',
  styleUrls: ['./update-hospital.component.css']
})
export class UpdateHospitalComponent implements OnInit {
  id: bigint;
  hospital: Hospital;
  constructor(private route: ActivatedRoute, private router: Router,
    private hospitalService: HospitalService) { }

  ngOnInit(): void {
    this.hospital = new Hospital();

    this.id = this.route.snapshot.params['id'];

    this.hospitalService.get(this.id)
      .subscribe(data => {
        console.log(data);
        this.hospital = data;
      }, error => console.log(error));
  }

  updateHospital(){
    // this.tutorial = new Tutorial();
    // this.id = this.route.snapshot.params['id'];
    this.hospitalService.update(this.id, this.hospital).subscribe(
      data => {
        console.log(data);
        this.hospital = new Hospital();
        this.list();
      }, error => console.log(error)
    );
  }
  onSubmit(){
    this.updateHospital();
  }
  list(){
    this.router.navigate(['hospitals']);
  }


}
