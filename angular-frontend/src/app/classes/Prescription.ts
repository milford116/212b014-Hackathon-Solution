

import {Medicines} from './Medicine';
import {Diets} from './Diet';

export class Prescription
{
     id:string;

     prescriptionId:bigint;
     regId:bigint;
     doctorId:bigint;
     createdOn:Date;
     modifiedOn:Date;
     bloodPressure:number;
     pulseRate:bigint;
     weight:number;
     allergies:string[];
     disabilities:string[];
     medicines:Medicines[];
     diets:Diets[];
     history:string;
     followDoctorId:bigint;
}