package com.schertz.assessment.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public List<Employee> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String firstName, String lastName);
}
