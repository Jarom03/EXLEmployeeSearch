package com.schertz.assessment.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	public List<Employee> findByFirstNameLikeOrLastNameLike(String firstName, String lastName);
}
