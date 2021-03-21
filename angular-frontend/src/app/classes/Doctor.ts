import { Binary } from "@angular/compiler";

export class Doctor
{
     id:string;
     doctor_id:bigint;
     hospital_id:bigint;
     doctor_name:string;
     speciality:string;
     address:string;
     about:string;
     profile_pic:Binary;
     created_on:Date;

}