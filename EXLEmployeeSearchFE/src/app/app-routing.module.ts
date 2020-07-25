import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AddEmployeeComponent } from './add-employee/add-employee.component';
import {SearchEmployeeComponent } from './search-employee/search-employee.component';


const routes: Routes = [

  {
    path: '',
    redirectTo: '/search',
    pathMatch: 'full'
  },
  {
    path: 'add',
    component: AddEmployeeComponent
  },
  {
    path: 'search',
    component: SearchEmployeeComponent
  },
  { 
    path: '**', 
    redirectTo: '/search' 
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
