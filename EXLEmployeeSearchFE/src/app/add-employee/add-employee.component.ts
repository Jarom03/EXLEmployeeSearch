import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

import { Employee } from '../objects/employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employeeForm: FormGroup;

  constructor(private employeeService: EmployeeService, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.buildForm();
  }

  /**
   * builds the reactive form
   */
  public buildForm() {
    this.employeeForm = new FormGroup({
      firstName: new FormControl('', Validators.required),
      lastName: new FormControl('', Validators.required),
      jobTitle: new FormControl('', Validators.required),
      birthDate: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('')
    });
  }

  /**
   * This is the action when the submit button is pressed
   */
  public submitForm() {
    if (!this.employeeForm.invalid) {
      let employee = new Employee();
      employee.firstName = this.employeeForm.get("firstName").value;
      employee.lastName = this.employeeForm.get("lastName").value;
      employee.jobTitle = this.employeeForm.get("jobTitle").value;
      employee.birthDate = this.employeeForm.get("birthDate").value;
      employee.startDate = this.employeeForm.get("startDate").value;

      this.employeeService.saveEmployee(employee);
      this._snackBar.openFromComponent(EmployeeAddedComponent, {
        duration: 10000,
      });
      // this.buildForm();
      this.employeeForm.reset();
    }
  }

  public clearForm() {
    this.employeeForm.reset();
  }

}


@Component({
  selector: 'snack-bar-component-employee-added',
  template: `<span class="snack-bar-component">New Employee Added!</span>`,
  styles: [`
    .snack-bar-component {
      color: white;
    }
  `],
})
export class EmployeeAddedComponent { }
