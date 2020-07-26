import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { Employee } from '../objects/employee';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employeeForm: FormGroup;

  constructor() { }

  ngOnInit(): void {
    this.buildForm();
  }

  /**
   * builds the reactive form
   */
  private buildForm() {
    this.employeeForm = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      jobTitle: new FormControl(''),
      birthDate: new FormControl(new Date()),
      startDate: new FormControl(new Date())
    });
  }

  /**
   * This is the action when the submit button is pressed
   */
  public submitForm() {
    console.log("submit button press");
    let employee = new Employee();
    employee.firstName = this.employeeForm.get("firstName").value;
    employee.lastName = this.employeeForm.get("lastName").value;
    employee.jobTitle = this.employeeForm.get("jobTitle").value;
    employee.birthDate = this.employeeForm.get("birthDate").value;
    employee.startDate = this.employeeForm.get("startDate").value;

    console.log(JSON.stringify(employee));
  }

}
