import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../objects/employee';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-search-employee',
  templateUrl: './search-employee.component.html',
  styleUrls: ['./search-employee.component.css']
})
export class SearchEmployeeComponent implements OnInit {
  
  displayedColumns: string[] = ['firstName', 'lastName', 'jobTitle', 'age', 'startDate', 'endDate'];
  public dataSource: Employee[];

  searchForm: FormGroup;

  constructor(private employeeService: EmployeeService) { }

  /**
   * builds the reactive form
   */
  private buildForm() {
    this.searchForm = new FormGroup({
      name: new FormControl(''),
    });
  }

  ngOnInit(): void {
    this.buildForm();
    this.dataSource = [];
    this.employeeService.getAllEmployees().then(employees => {
      this.dataSource = employees as Employee[];
    });

    // this.employeeService.searchEmployees("nol").then(employees => {
    //   console.log(employees);
    // });
  }

  public submitForm() {
    this.employeeService.searchEmployees(this.searchForm.get("name").value).then(result => {
      this.dataSource = result as Employee[];
    });
  }

}
