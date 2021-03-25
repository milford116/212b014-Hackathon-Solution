import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Prescription } from '../classes/Prescription';

const baseUrl = 'http://localhost:8080/api/prescriptions';
@Injectable({
  providedIn: 'root'
})
export class PrescriptionService {

  private allergies: string[] = [
    'Nuts',
    'Pollen',
    'Dust mites',
    'Mold'
  ];

  private disabilities: string[] = [
    'Arthritis',
    'Deaf',
    'Blind',
    'Mute',
    'Cerebral palsy',
    'Spinal cord injury',
    'Muscular dystrophy'
  ];

  private diets: string[] = [
    'Keto',
    'Low Fat',
    'Vegetarian',
    'Carnivore',
  ];

  private reportSubject = new Subject<any>();

  constructor(private http: HttpClient) { }

  sendListUpdateAlert(reportMessage: string) {
    this.reportSubject.next({ text: reportMessage });
  }

  getListUpdateAlert(): Observable<any> {
    return this.reportSubject.asObservable();
  }

  getAllergies() {
    return this.allergies.slice();
  }

  getDisabilities() {
    return this.disabilities.slice();
  }

  

  getDiets() {
    return this.diets.slice();
  }

  getAll(): Observable<Prescription[]> {
    return this.http.get<Prescription[]>(baseUrl);
  }

  get(id: string): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }

  create(data: object): Observable<object> {
    return this.http.post(baseUrl, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.put(`${ baseUrl }/${ id }`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${ baseUrl }/${ id }`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
 getprescripbynameanddob(data:object):Observable<any> {
    
    return this.http.post(`${baseUrl}/name/dob`,data);
  }
}
