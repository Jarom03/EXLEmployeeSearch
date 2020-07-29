import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { Employee } from '../objects/employee';
import { FormGroup, FormControl } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-search-employee',
  templateUrl: './search-employee.component.html',
  styleUrls: ['./search-employee.component.css']
})
export class SearchEmployeeComponent implements OnInit {

  displayedColumns: string[] = ['firstName', 'lastName', 'jobTitle', 'age', 'startDate', 'endDate'];
  public dataSource: MatTableDataSource<Employee>;

  searchForm: FormGroup;

  constructor(private employeeService: EmployeeService) { }

  /**
   * builds the reactive form
   */
  private buildForm() {
    this.searchForm = new FormGroup({
      name: new FormControl(''),
      fromDate: new FormControl(undefined),
      toDate: new FormControl(undefined)
    });
  }

  ngOnInit(): void {
    this.buildForm();
    this.employeeService.getAllEmployees().then(employees => {
      this.dataSource = new MatTableDataSource(employees as Employee[]);

      // Override the default filter predicate to filter based on user defined search
      // criteria.
      this.dataSource.filterPredicate = (data: Employee, filter: string) => {
        if (!this.searchForm) {
          console.log("searchForm is false");
          return true;
        }

        let nameFilter = this.searchForm.get("name").value.toLowerCase();
        //if the from date is not set it will default to Dec 31, 1969
        let filterStart = new Date(this.searchForm.get("fromDate").value);
        //if the filter end date is not we will default to todays date
        let filterEnd = new Date(this.searchForm.get("toDate").value == null ? Date.now() : this.searchForm.get("toDate").value);
        

        let startDate = new Date(data.startDate);
        // If the employee does not have an end date we will use today's date since we have 
        // no idea when they might leave the company we cannot predict future
        let endDate = new Date(); 
        if (data.endDate) {
          endDate = new Date(data.endDate);
        }
        return (
                  data.firstName.toLowerCase().includes(nameFilter) 
                  || data.lastName.toLowerCase().includes(nameFilter)) 
                  && ((startDate <= filterStart && filterStart <= endDate) 
                  || (startDate <= filterEnd && filterEnd <= endDate) 
                  || (startDate > filterStart && endDate < filterEnd)
                );
      };
    });


  }

  // I considered retrieving the search results from the backend, but decided to do all 
  // on client side to reduce network traffic. If the dataset was very large I would reconsider
  // this approach
  public submitForm() {
    this.employeeService.searchEmployees(this.searchForm.get("name").value).then(result => {
      this.dataSource = new MatTableDataSource(result as Employee[]);
    });
  }

  /**
   * Activate the search (filter)
   */
  applyFilter() {
    // Since the overridden filterPredicate doesn't use this filter value but the actual form values, 
    // it's value doesn't matter
    this.dataSource.filter = "NA";
  }

}
