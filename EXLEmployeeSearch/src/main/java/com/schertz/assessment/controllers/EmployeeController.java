package com.schertz.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.schertz.assessment.models.Employee;
import com.schertz.assessment.models.EmployeeRepository;

/**
 * @author Jarom Schertz
 * Rest Controller for Employee CRUD services
 */
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	/**
	 * Saves the employee into the database
	 * NOTE: Configured CORS to allow any site origin for ease of testing. Typically this would be 
	 * restricted to valid origins.
	 */
	@CrossOrigin(origins="*", methods=RequestMethod.PUT) 
	@PutMapping("/saveEmployee")
	public ResponseEntity<String> saveEmployee(@RequestBody Employee employee) {
		employeeRepo.save(employee);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body("Employee Saved");
	}
	
	@CrossOrigin(origins="*", methods=RequestMethod.GET) 
	@GetMapping("/getAllEmployees")
	public ResponseEntity<Iterable<Employee>> getAllEmployees() {
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(employeeRepo.findAll());
	}
	
	@CrossOrigin(origins="*", methods=RequestMethod.GET) 
	@GetMapping("/searchEmployee")
	public ResponseEntity<Iterable<Employee>> searchForEmployees(@RequestParam("name") String nameString) {
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(employeeRepo.findByFirstNameLikeOrLastNameLike(nameString, nameString));
		
	}
	
	
}
