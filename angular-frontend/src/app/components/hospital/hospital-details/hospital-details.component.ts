import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { from, Observable } from 'rxjs';
import { Hospital } from 'src/app/classes/Hospital';
import {HospitalService} from 'src/app/services/hospital.service';

@Component({
  selector: 'app-hospital-details',
  templateUrl: './hospital-details.component.html',
  styleUrls: ['./hospital-details.component.css']
})
export class HospitalDetailsComponent implements OnInit {
  id: bigint;
  hospital:Hospital;

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

  list(){
    this.router.navigate(['hospitals']);
  }
  updateLink(){
    this.router.navigate(['hospitals/getupdate/' + this.hospital.id]);     //link thik kora lagbe
  }

}
