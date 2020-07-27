import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../environments/environment';
import { Employee } from './objects/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  /**
   * Saves employee to database
   * @param employee the employee object to be saved
   */
  public saveEmployee(employee: Employee): Promise<string> {
    return this.http.put(environment.backendHost + "/saveEmployee", JSON.stringify(employee), {headers: {'Content-Type':'application/json; charset=utf-8'}},).toPromise().then(response => {
      return response as string;
    }).catch(this.handleError);
  }

  /**
   * Returns all employees
   */
  public getAllEmployees(): Promise<Employee[]> {
    return this.http.get(environment.backendHost + "/getAllEmployees").toPromise().then(result => {
      return result as Employee[];
    });
  }

  /**
   * Search for employee by full or partial first or last name
   * @param name the partial or full name that will be searched by
   */
  public searchEmployees(name: string): Promise<Employee[]> {
    return this.http.get(encodeURI(environment.backendHost + "/searchEmployee?name=%" + name + "%")).toPromise().then(result => {
      return result as Employee[];
    });
  }

  private handleError(error: any): Promise<string> {
    return Promise.resolve("Error communicating with server, please try again later.");
  }

}
