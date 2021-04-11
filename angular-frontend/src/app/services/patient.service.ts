import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import {Patient} from '../classes/Patient';

const baseUrl = 'http://localhost:8080/api/patients';
@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private patientSubject = new Subject<any>();

  constructor(private http: HttpClient) { }
  sendListUpdateAlert(patientMessage: string) {
    this.patientSubject.next({ text: patientMessage });
  }

  getListUpdateAlert(): Observable<any> {
    return this.patientSubject.asObservable();
  }


  getAll(): Observable<Patient[]> {
    return this.http.get<Patient[]>(baseUrl);
  }
  get(id: string): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  
  getid(id:bigint):Observable<any>{
    return this.http.get(`${baseUrl}/regid/${id}`);
  }

  create(data: object): Observable<object> {
    return this.http.post(baseUrl, data);
  }

  update(id: string, data: object): Observable<object> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }

  delete(id: string): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
  findByPatient_name(name:string):Observable<any> {
    return this.http.post(`${baseUrl}/${name}`,"");
  }
}
