import { Employee } from './../model/employee';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class EmployeeService {

  constructor(private _http: HttpClient) { }

  getEmployees(): Observable<Array<Employee>> {
    const url = '/employee/';
    return this._http.get<Array<Employee>>(url);
  }
}
