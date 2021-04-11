import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Invoice } from '../classes/Invoice';

const baseUrl = 'http://localhost:8080/api/invoices';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  private invoiceSubject = new Subject<any>();

  constructor(private http: HttpClient) { }

  getAll(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(baseUrl);
  }
  get(id: string): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
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
}
